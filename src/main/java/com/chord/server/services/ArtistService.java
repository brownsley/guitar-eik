package com.chord.server.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.chord.server.dto.request.ArtistCreateDto;
import com.chord.server.entities.Artist;
import com.chord.server.exception.ResourceAlreadyExistsException;
import com.chord.server.projections.ArtistDetailSummary;
import com.chord.server.projections.ArtistSummary;
import com.chord.server.repositories.ArtistRepository;

@Service
public class ArtistService {
    private final ArtistRepository artistRepository;

    public ArtistService(ArtistRepository artistRepository) {
        this.artistRepository = artistRepository;
    }

    public List<ArtistSummary> searchArtist(String query) {
        return artistRepository.findByNameContainingIgnoreCase(query);
    }

    public void artistCreate(ArtistCreateDto createDto) {
        Artist artist = new Artist();
        if (artistRepository.existsByName(createDto.getName())) {
            throw new ResourceAlreadyExistsException(createDto.getName() + " already exists");
        }
        artist.setAvater(createDto.getAvater());
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
