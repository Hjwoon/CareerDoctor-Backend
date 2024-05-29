package com.homepage.careerdoctor.specReport.controller;

import com.homepage.careerdoctor.specReport.dto.SpecReportWriteRequestDto;
import com.homepage.careerdoctor.specReport.service.SpecReportServiceImpl;
import com.homepage.careerdoctor.util.response.CustomApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/careerdoctor")
@RequiredArgsConstructor
public class SpecReportController {

    private final SpecReportServiceImpl specReportService;

    @PostMapping("/{specId}/write-report") // 소견서 작성
    private ResponseEntity<CustomApiResponse<?>> writeReport(@PathVariable Long specId, @Valid @RequestBody SpecReportWriteRequestDto dto) {

        return specReportService.writeReport(specId, dto);
    }

    @GetMapping("/reports/all")
    private ResponseEntity<CustomApiResponse<?>> getReports() {
        return specReportService.getReports();
    }

    @GetMapping("/reports/{userId}")
    private ResponseEntity<CustomApiResponse<?>> getAllMyReport(@PathVariable String userId) {
        return specReportService.getAllMyReport(userId);
    }

    @GetMapping("/reports/{userId}/{reportId}")
    private ResponseEntity<CustomApiResponse<?>> getMyReport(@PathVariable String userId, @PathVariable Long reportId) {
        return specReportService.getMyReport(userId, reportId);
    }

}
