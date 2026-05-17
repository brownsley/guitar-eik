package com.chord.server.repositories.music;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import com.chord.server.entities.music.Album;
import com.chord.server.projections.AlbumDetailSummary;
import com.chord.server.projections.AlbumSummary;

public interface AlbumRepository extends JpaRepository<Album, Long> {

    boolean existsById(Long id);

    AlbumDetailSummary findProjectedById(Long id);

    List<AlbumSummary> findByNameContainingIgnoreCase(String name);

    boolean existsByName(String name);

    Page<AlbumSummary> findBy(PageRequest pageable);
}
