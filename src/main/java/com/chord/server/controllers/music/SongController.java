package com.chord.server.controllers.music;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.chord.server.dto.request.music.SongCreateDto;
import com.chord.server.entities.music.Song;
import com.chord.server.projections.SongSummary;
import com.chord.server.services.music.SongService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/songs")
public class SongController {
    private final SongService songService;

    public SongController(SongService songService) {
        this.songService = songService;
    }

    @PostMapping
    public void spngCreate(@Valid @RequestBody SongCreateDto createDto) {
        songService.songCreate(createDto);
    }

    @GetMapping("/search")
    public List<SongSummary> seatch(@RequestParam(name = "query", required = false) String query) {
        return songService.searchSongs(query);
    }

    @GetMapping("/{id}")
    public Song getSongDetail(@PathVariable Long id) {
        return songService.getSongDetail(id);
    }

    @GetMapping
    public Page<SongSummary> getAllSongsSummary(@RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int count) {
        return songService.getAllSongsSummary(page, count);
    }

}
