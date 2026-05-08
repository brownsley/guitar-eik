package com.chord.server.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.chord.server.entities.Song;
import com.chord.server.projections.SongSummary;

public interface SongRepository extends JpaRepository<Song, Long> {

    boolean existsByTitle(String title);

    Page<SongSummary> findBy(Pageable pageable);

    List<SongSummary> findByTitleContainingIgnoreCase(String title);

}
