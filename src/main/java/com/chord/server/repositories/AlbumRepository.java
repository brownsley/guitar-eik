package com.chord.server.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.chord.server.entities.Album;
import com.chord.server.projections.AlbumSummary;

public interface AlbumRepository extends JpaRepository<Album, Long> {

    boolean existsById(Long id);

    boolean existsByName(String name);

    List<AlbumSummary> findAllProjectedBy();
}
