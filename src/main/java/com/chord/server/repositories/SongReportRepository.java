package com.chord.server.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.chord.server.entities.SongReport;

public interface SongReportRepository extends JpaRepository<SongReport, Long> {

}
