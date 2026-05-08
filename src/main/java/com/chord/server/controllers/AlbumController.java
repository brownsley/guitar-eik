package com.chord.server.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chord.server.dto.request.AlbumCreateDto;
import com.chord.server.entities.Album;
import com.chord.server.projections.AlbumSummary;
import com.chord.server.services.AlbumService;

@RestController
@RequestMapping("/album")
public class AlbumController {
    private final AlbumService albumService;

    public AlbumController(AlbumService albumService) {
        this.albumService = albumService;
    }

    @GetMapping
    public List<AlbumSummary> getAllAlbumSummaries() {
        return this.albumService.getAllAlbumSummaries();
    }

    @GetMapping("/{id}")
    public Album getAblumById(@PathVariable Long id) {
        return this.albumService.getAlbumById(id);
    }

    @PostMapping
    public void createAlbum(@RequestBody AlbumCreateDto createDto) {
        this.albumService.createAlbum(createDto);
    }

}
