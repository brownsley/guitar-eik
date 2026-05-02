package com.chord.server.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.chord.server.dto.request.SongCreateDto;
import com.chord.server.entities.Artist;
import com.chord.server.entities.Song;
import com.chord.server.exception.ResourceNotFoundException;
import com.chord.server.projections.SongSummary;
import com.chord.server.repositories.ArtistRepository;
import com.chord.server.repositories.SongRepository;

@Service
public class SongService {

    private final ArtistRepository artistRepository;
    private final SongRepository songRepository;

    public SongService(SongRepository songRepository, ArtistRepository artistRepository) {
        this.songRepository = songRepository;
        this.artistRepository = artistRepository;
    }

    public void songCreate(SongCreateDto createDto) {
        Song song = new Song();
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
