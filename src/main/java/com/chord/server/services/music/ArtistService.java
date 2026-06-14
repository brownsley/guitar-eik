package com.chord.server.services.music;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.chord.server.dto.request.music.ArtistCreateDto;
import com.chord.server.entities.music.Artist;
import com.chord.server.exception.ResourceAlreadyExistsException;
import com.chord.server.projections.ArtistDetailSummary;
import com.chord.server.projections.ArtistSummary;
import com.chord.server.repositories.music.ArtistRepository;
import com.chord.server.services.storage.ImageStorageService;

@Service
public class ArtistService {
    private final ImageStorageService imageStorageService;
    private final ArtistRepository artistRepository;

    public ArtistService(ArtistRepository artistRepository, ImageStorageService imageStorageService) {
        this.artistRepository = artistRepository;
        this.imageStorageService = imageStorageService;
    }

    public List<ArtistSummary> searchArtists(String query) {
        return artistRepository.findByNameContainingIgnoreCase(query);
    }

    public void artistCreate(ArtistCreateDto createDto, MultipartFile file) {
        if (artistRepository.existsByName(createDto.getName())) {
            throw new ResourceAlreadyExistsException(createDto.getName() + " already exists");
        }
        String avatarFileName = imageStorageService.imageUpload(file);

        Artist artist = new Artist();
        artist.setAvatar(avatarFileName);
        artist.setName(createDto.getName());
        artist.setSocialLink(createDto.getSocialLink());
        artistRepository.save(artist);
    }

    public ArtistDetailSummary getArtistDetail(Long id) {
        return artistRepository.findProjectedById(id);
    }

    public Page<ArtistSummary> getAllArsistsSummary(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("id").descending());
        return artistRepository.findBy(pageable);
    }
}
