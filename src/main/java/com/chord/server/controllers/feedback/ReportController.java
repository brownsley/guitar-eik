package com.chord.server.controllers.feedback;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.chord.server.dto.request.feedback.ReportDto;
import com.chord.server.entities.feedback.SongReport;
import com.chord.server.services.feedback.ReportService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/report")
public class ReportController {
    private final ReportService reportService;

    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    @PostMapping
    public void createReport(@RequestBody ReportDto reportDto) {
        this.reportService.createReport(reportDto);
    }

    @GetMapping("/{id}")
    public SongReport getReportById(@PathVariable Long id) {
        return reportService.getReportById(id);
    }

    @GetMapping
    public List<SongReport> getAllReports() {
        return reportService.getAllReports();
    }

    @PutMapping("/status")
    public void reportStatusChange(@Valid @RequestParam Long songId, @RequestParam int status) {
        this.reportService.statusChange(songId, status);
    }
}
