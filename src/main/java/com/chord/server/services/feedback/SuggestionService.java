package com.chord.server.services.feedback;

import java.util.List;

import org.springframework.stereotype.Service;

import com.chord.server.dto.request.feedback.SuggestionDto;
import com.chord.server.entities.feedback.Suggestion;
import com.chord.server.exception.ResourceNotFoundException;
import com.chord.server.repositories.feedback.SuggestionRepository;

@Service
public class SuggestionService {
    private final SuggestionRepository suggestionRepository;

    public SuggestionService(SuggestionRepository suggestionRepository) {
        this.suggestionRepository = suggestionRepository;
    }

    public Suggestion getSuggestionById(Long id) {
        return suggestionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Suggestion not found"));
    }

    public List<Suggestion> getAllSuggestions() {
        return suggestionRepository.findAll();
    }

    public void createSuggestion(SuggestionDto suggestionDto) {
        Suggestion suggestion = new Suggestion();
        suggestion.setSubject(suggestionDto.getSubject());
        suggestion.setDescription(suggestionDto.getDescription());
        suggestionRepository.save(suggestion);
    }
}
