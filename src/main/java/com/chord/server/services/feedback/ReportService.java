package com.chord.server.services.feedback;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.chord.server.dto.request.feedback.ReportDto;
import com.chord.server.entities.feedback.SongReport;
import com.chord.server.entities.music.Song;
import com.chord.server.enums.ReportStatus;
import com.chord.server.exception.ResourceNotFoundException;
import com.chord.server.repositories.feedback.ReportRepository;
import com.chord.server.repositories.music.SongRepository;

@Service
public class ReportService {
    private final SongRepository songRepository;
    private final ReportRepository songReportRepository;

    public ReportService(ReportRepository songReportRepository, SongRepository songRepository) {
        this.songReportRepository = songReportRepository;
        this.songRepository = songRepository;
    }

    @Transactional
    public void createReport(ReportDto reportDto) {
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

    public SongReport getReportById(Long id) {
        SongReport report = songReportRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Report not found"));
        return report;
    }

    public List<SongReport> getAllReports() {
        return songReportRepository.findAll();
    }
}
