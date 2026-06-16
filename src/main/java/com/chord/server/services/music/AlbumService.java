package com.chord.server.services.music;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.chord.server.dto.request.music.AlbumCreateDto;
import com.chord.server.entities.music.Album;
import com.chord.server.entities.music.Artist;
import com.chord.server.entities.music.Song;
import com.chord.server.exception.ResourceAlreadyExistsException;
import com.chord.server.projections.AlbumDetailSummary;
import com.chord.server.projections.AlbumSummary;
import com.chord.server.repositories.music.AlbumRepository;
import com.chord.server.repositories.music.ArtistRepository;
import com.chord.server.repositories.music.SongRepository;
import com.chord.server.services.storage.ImageStorageService;

@Service
public class AlbumService {
    private final SongRepository songRepository;
    private final ArtistRepository artistRepository;
    private final AlbumRepository albumRepository;

    private final ImageStorageService imageStorageService;

    public AlbumService(AlbumRepository albumRepository, ArtistRepository artistRepository,
            SongRepository songRepository, ImageStorageService imageStorageService) {
        this.albumRepository = albumRepository;
        this.artistRepository = artistRepository;
        this.songRepository = songRepository;
        this.imageStorageService = imageStorageService;
    }

    public Page<AlbumSummary> getAllAlbumSummaries(int page, int size) {
        PageRequest pageable = PageRequest.of(page, size, Sort.by("id").descending());
        return this.albumRepository.findBy(pageable);
    }

    public List<AlbumSummary> searchAlbums(String query) {
        return this.albumRepository.findByNameContainingIgnoreCase(query);
    }

    public AlbumDetailSummary getAlbumById(long id) {
        return this.albumRepository.findProjectedById(id);
    }

    @Transactional
    public void createAlbum(AlbumCreateDto createDto, MultipartFile file) {
        if (albumRepository.existsByName(createDto.getName())) {
            throw new ResourceAlreadyExistsException("Album already exists");
        }

        Album album = new Album();
        album.setName(createDto.getName());

        if (file != null && !file.isEmpty()) {
            String cover = imageStorageService.imageUpload(file, "album");
            album.setCover(cover);
        }
        Album savedAlbum = albumRepository.save(album);

        Set<Artist> aggregatedArtists = new HashSet<>();

        if (createDto.getSongs() != null && !createDto.getSongs().isEmpty()) {
            List<Song> songs = songRepository.findAllById(createDto.getSongs());

            songs.forEach(song -> {
                song.getAlbums().add(album);
                if (song.getArtists() != null) {
                    aggregatedArtists.addAll(song.getArtists());
                }
            });
            album.setSongs(new ArrayList<>(songs));
        }

        if (createDto.getArtists() != null && !createDto.getArtists().isEmpty()) {
            List<Artist> manualArtists = artistRepository.findAllById(createDto.getArtists());
            aggregatedArtists.addAll(manualArtists);
        }

        album.setArtists(new ArrayList<>(aggregatedArtists));
        albumRepository.save(album);
    }
}
