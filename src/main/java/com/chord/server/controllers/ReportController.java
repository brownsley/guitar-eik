package com.chord.server.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.chord.server.dto.request.SongReportDto;
import com.chord.server.services.ReportService;

@RestController
@RequestMapping("/report")
public class ReportController {
    private final ReportService reportService;

    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    @PostMapping
    public void createReport(@RequestBody SongReportDto reportDto) {
        this.reportService.createReport(reportDto);
    }

    @PutMapping("/status")
    public void reportStatusChange(@RequestParam Long songId, @RequestParam int status) {
        this.reportService.statusChange(songId, status);
    }
}
