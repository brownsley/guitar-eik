package com.chord.server.repositories.feedback;

import org.springframework.data.jpa.repository.JpaRepository;

import com.chord.server.entities.feedback.Suggestion;

public interface SuggestionRepository extends JpaRepository<Suggestion, Long> {

}
