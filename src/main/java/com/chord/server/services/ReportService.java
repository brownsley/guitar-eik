package com.chord.server.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.chord.server.dto.request.SongReportDto;
import com.chord.server.entities.Song;
import com.chord.server.entities.SongReport;
import com.chord.server.enums.ReportStatus;
import com.chord.server.exception.ResourceNotFoundException;
import com.chord.server.repositories.SongReportRepository;
import com.chord.server.repositories.SongRepository;

@Service
public class ReportService {
    private final SongRepository songRepository;
    private final SongReportRepository songReportRepository;

    public ReportService(SongReportRepository songReportRepository, SongRepository songRepository) {
        this.songReportRepository = songReportRepository;
        this.songRepository = songRepository;
    }

    @Transactional
    public void createReport(SongReportDto reportDto) {
        Song song = songRepository.findById(reportDto.getSongId())
                .orElseThrow(() -> new ResourceNotFoundException("Song Not Found With Id: " + reportDto.getSongId()));
        SongReport songReport = new SongReport();
        songReport.setSubject(reportDto.getSubject());
        songReport.setSongId(song.getId());
        songReport.setDescription(reportDto.getDescription());

        songReportRepository.save(songReport);
    }

    @Transactional
    public void statusChange(Long reportId, int status) {
        SongReport songReport = songReportRepository.findById(reportId)
                .orElseThrow(() -> new ResourceNotFoundException("Report Not Found"));
        songReport.setStatus(ReportStatus.fromInt(status));
        songReportRepository.save(songReport);
    }
}
