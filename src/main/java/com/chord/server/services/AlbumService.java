package com.chord.server.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.chord.server.dto.request.AlbumCreateDto;
import com.chord.server.entities.Album;
import com.chord.server.entities.Artist;
import com.chord.server.entities.Song;
import com.chord.server.exception.ResourceAlreadyExistsException;
import com.chord.server.projections.AlbumDetailSummary;
import com.chord.server.projections.AlbumSummary;
import com.chord.server.repositories.AlbumRepository;
import com.chord.server.repositories.ArtistRepository;
import com.chord.server.repositories.SongRepository;

@Service
public class AlbumService {
    private final SongRepository songRepository;
    private final ArtistRepository artistRepository;
    private final AlbumRepository albumRepository;

    public AlbumService(AlbumRepository albumRepository, ArtistRepository artistRepository,
            SongRepository songRepository) {
        this.albumRepository = albumRepository;
        this.artistRepository = artistRepository;
        this.songRepository = songRepository;
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

    public void createAlbum(AlbumCreateDto createDto) {
        if (albumRepository.existsByName(createDto.getName())) {
            throw new ResourceAlreadyExistsException("Album already exists");
        }
        Album album = new Album();
        album.setName(createDto.getName());
        album.setCover(createDto.getCover());

        if (createDto.getSongs() != null && !createDto.getSongs().isEmpty()) {
            List<Song> songs = songRepository.findAllById(createDto.getSongs());
            for (Song song : songs) {
                album.getSongs().add(song);
            }
        }
        if (createDto.getArtists() != null && !createDto.getArtists().isEmpty()) {
            List<Artist> artists = artistRepository.findAllById(createDto.getArtists());
            for (Artist artist : artists) {
                album.getArtists().add(artist);
            }
        }
        albumRepository.save(album);
    }
}
