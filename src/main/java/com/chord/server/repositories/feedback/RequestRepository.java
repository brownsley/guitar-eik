package com.chord.server.repositories.feedback;

import org.springframework.data.jpa.repository.JpaRepository;

import com.chord.server.entities.feedback.SongRequest;

public interface RequestRepository extends JpaRepository<SongRequest, Long> {

}
