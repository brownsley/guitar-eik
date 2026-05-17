package com.chord.server.services.music;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.chord.server.dto.request.music.SongCreateDto;
import com.chord.server.entities.music.Album;
import com.chord.server.entities.music.Artist;
import com.chord.server.entities.music.Song;
import com.chord.server.exception.ResourceAlreadyExistsException;
import com.chord.server.exception.ResourceNotFoundException;
import com.chord.server.projections.SongSummary;
import com.chord.server.repositories.music.AlbumRepository;
import com.chord.server.repositories.music.ArtistRepository;
import com.chord.server.repositories.music.SongRepository;

@Service
public class SongService {

    private final AlbumRepository albumRepository;
    private final ArtistRepository artistRepository;
    private final SongRepository songRepository;

    public SongService(SongRepository songRepository, ArtistRepository artistRepository,
            AlbumRepository albumRepository) {
        this.songRepository = songRepository;
        this.artistRepository = artistRepository;
        this.albumRepository = albumRepository;
    }

    public List<SongSummary> searchSongs(String query) {
        return songRepository.findByTitleContainingIgnoreCase(query);
    }

    public void songCreate(SongCreateDto createDto) {
        Song song = new Song();
        if (songRepository.existsByTitle(createDto.getTitle())) {
            throw new ResourceAlreadyExistsException(createDto.getTitle() + "already exists");
        }
        if (createDto.getAlbumId() != null) {
            Album album = albumRepository.findById(createDto.getAlbumId())
                    .orElseThrow(() -> new ResourceNotFoundException("Album Not Found"));
            song.setAlbum(album);
        }
        song.setTitle(createDto.getTitle());
        song.setLyric(createDto.getLyric());
        List<Artist> artists = artistRepository.findAllById(createDto.getArtists());
        if (artists.isEmpty()) {
            throw new ResourceNotFoundException("Artist Not Found");
        }
        song.setArtists(artists);
        songRepository.save(song);
    }

    public Song getSongDetail(Long id) {
        return songRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Song Not Found"));
    }

    public Page<SongSummary> getAllSongsSummary(int page, int count) {
        Pageable pageable = PageRequest.of(page, count, Sort.by("id").descending());
        return songRepository.findBy(pageable);
    }
}
