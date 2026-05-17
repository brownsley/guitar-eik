package com.chord.server.repositories.music;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.chord.server.entities.music.Artist;
import com.chord.server.projections.ArtistDetailSummary;
import com.chord.server.projections.ArtistSummary;

public interface ArtistRepository extends JpaRepository<Artist, Long> {

    Page<ArtistSummary> findBy(Pageable pageable);

    boolean existsByName(String name);

    ArtistDetailSummary findProjectedById(Long id);

    List<ArtistSummary> findByNameContainingIgnoreCase(String name);
}
