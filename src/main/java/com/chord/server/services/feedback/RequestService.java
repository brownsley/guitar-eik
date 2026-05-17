package com.chord.server.services.feedback;

import java.util.List;

import org.springframework.stereotype.Service;

import com.chord.server.dto.request.feedback.RequestDto;
import com.chord.server.entities.feedback.SongRequest;
import com.chord.server.exception.ResourceNotFoundException;
import com.chord.server.repositories.feedback.RequestRepository;

@Service
public class RequestService {
    private final RequestRepository songRequestRepository;

    public RequestService(RequestRepository songRequestRepository) {
        this.songRequestRepository = songRequestRepository;
    }

    public List<SongRequest> getAllSongRequests() {
        return songRequestRepository.findAll();
    }

    public SongRequest getSongRequestById(Long id) {
        return songRequestRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Song request not found"));
    }

    public void createRequest(RequestDto requestDto) {
        SongRequest songRequest = new SongRequest();
        songRequest.setTitle(requestDto.getTitle());
        songRequest.setArtist(requestDto.getArtist());
        songRequestRepository.save(songRequest);
    }
}
