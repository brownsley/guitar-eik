package com.chord.server.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.chord.server.entities.Artist;
import com.chord.server.projections.ArtistDetailSummary;
import com.chord.server.projections.ArtistSummary;

public interface ArtistRepository extends JpaRepository<Artist, Long> {

    List<ArtistSummary> findAllProjectedBy();

    Page<ArtistSummary> findBy(Pageable pageable);

    ArtistDetailSummary findProjectedById(Long id);
}
