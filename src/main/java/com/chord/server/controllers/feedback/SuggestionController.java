package com.chord.server.controllers.feedback;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chord.server.dto.request.feedback.SuggestionDto;
import com.chord.server.entities.feedback.Suggestion;
import com.chord.server.services.feedback.SuggestionService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/suggestion")
public class SuggestionController {
    private final SuggestionService suggestionService;

    public SuggestionController(SuggestionService suggestionService) {
        this.suggestionService = suggestionService;
    }

    @GetMapping("/{id}")
    public Suggestion getSuggestionById(@PathVariable Long id) {
        return suggestionService.getSuggestionById(id);
    }

    @GetMapping
    public List<Suggestion> getAllSuggestions() {
        return suggestionService.getAllSuggestions();
    }

    @PostMapping
    public void postMethodName(@Valid @RequestBody SuggestionDto suggestionDto) {
        suggestionService.createSuggestion(suggestionDto);
    }

}
