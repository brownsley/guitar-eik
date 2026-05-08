package com.chord.server.controllers;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.chord.server.dto.request.AlbumCreateDto;
import com.chord.server.projections.AlbumDetailSummary;
import com.chord.server.projections.AlbumSummary;
import com.chord.server.services.AlbumService;

@RestController
@RequestMapping("/albums")
public class AlbumController {
    private final AlbumService albumService;

    public AlbumController(AlbumService albumService) {
        this.albumService = albumService;
    }

    @GetMapping()
    public Page<AlbumSummary> getAllAlbumSummaries(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        return albumService.getAllAlbumSummaries(page, size);
    }

    @GetMapping("/search")
    public List<AlbumSummary> search(@RequestParam(name = "query", required = false) String query) {
        return albumService.searchAlbums(query);
    }

    @GetMapping("/{id}")
    public AlbumDetailSummary getAblumById(@PathVariable Long id) {
        return albumService.getAlbumById(id);
    }

    @PostMapping
    public void createAlbum(@RequestBody AlbumCreateDto createDto) {
        albumService.createAlbum(createDto);
    }

}
