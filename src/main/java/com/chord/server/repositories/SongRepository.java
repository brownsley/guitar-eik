package com.chord.server.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.chord.server.entities.Song;
import com.chord.server.projections.SongSummary;

public interface SongRepository extends JpaRepository<Song, Long> {

    List<SongSummary> findAllProjectedBy();

    Page<SongSummary> findBy(Pageable pageable);

}
