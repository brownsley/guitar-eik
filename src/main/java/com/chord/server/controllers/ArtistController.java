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

import com.chord.server.dto.request.ArtistCreateDto;
import com.chord.server.entities.Artist;
import com.chord.server.projections.ArtistDetailSummary;
import com.chord.server.projections.ArtistSummary;
import com.chord.server.repositories.ArtistRepository;
import com.chord.server.services.ArtistService;

@RequestMapping("artists")
@RestController
public class ArtistController {
    private final ArtistRepository artistRepository;
    private final ArtistService artistService;

    public ArtistController(ArtistService artistService, ArtistRepository artistRepository) {
        this.artistService = artistService;
        this.artistRepository = artistRepository;
    }

    @PostMapping
    public void postMethodName(@RequestBody ArtistCreateDto createDto) {
        artistService.artistCreate(createDto);
    }

    @GetMapping("/{id}")
    public ArtistDetailSummary getArtistDetail(@PathVariable Long id) {
        return artistService.getArtistDetail(id);
    }

    @GetMapping("/raw")
    public List<Artist> getAllArsistsSummaryd() {
        return artistRepository.findAll();
    }

    @GetMapping
    public Page<ArtistSummary> getAllArsistsSummary(@RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int count) {
        return artistService.getAllArsistsSummary(page, count);
    }
}
