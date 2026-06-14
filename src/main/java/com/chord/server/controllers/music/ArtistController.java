package com.chord.server.controllers.music;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.chord.server.dto.request.music.ArtistCreateDto;
import com.chord.server.projections.ArtistDetailSummary;
import com.chord.server.projections.ArtistSummary;
import com.chord.server.services.music.ArtistService;

import jakarta.validation.Valid;

@RequestMapping("artists")
@RestController
public class ArtistController {
    private final ArtistService artistService;

    public ArtistController(ArtistService artistService) {
        this.artistService = artistService;
    }

    @PostMapping(consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
    public void postMethodName(
            @Valid @ModelAttribute ArtistCreateDto createDto,
            @RequestPart("file") MultipartFile file) {
        artistService.artistCreate(createDto, file);
    }

    @GetMapping("/search")
    public List<ArtistSummary> search(@RequestParam(name = "query", required = false) String query) {
        return artistService.searchArtists(query);
    }

    @GetMapping("/{id}")
    public ArtistDetailSummary getArtistDetail(@PathVariable Long id) {
        return artistService.getArtistDetail(id);
    }

    @GetMapping
    public Page<ArtistSummary> getAllArsistsSummary(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int count) {
        return artistService.getAllArsistsSummary(page, count);
    }
}
