package com.chord.server.controllers.feedback;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chord.server.dto.request.feedback.RequestDto;
import com.chord.server.entities.feedback.SongRequest;
import com.chord.server.services.feedback.RequestService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/songrequest")
public class RequestController {
    private final RequestService songRequestService;

    public RequestController(RequestService songRequestService) {
        this.songRequestService = songRequestService;
    }

    @GetMapping
    public List<SongRequest> getAllSongRequests() {
        return songRequestService.getAllSongRequests();
    }

    @GetMapping("/{id}")
    public SongRequest getSongRequestById(@PathVariable Long id) {
        return songRequestService.getSongRequestById(id);
    }

    @PostMapping
    public void createRequest(@Valid @RequestBody RequestDto requestDto) {
        songRequestService.createRequest(requestDto);
    }

}
