package com.homepage.careerdoctor.specReport.controller;

import com.homepage.careerdoctor.specReport.dto.SpecReportWriteDto;
import com.homepage.careerdoctor.specReport.service.SpecReportServiceImpl;
import com.homepage.careerdoctor.util.response.CustomApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/careerdoctor")
@RequiredArgsConstructor
public class SpecReportController {

    private final SpecReportServiceImpl specReportService;

    @PostMapping("/write-report") // 소견서 작성
    private ResponseEntity<CustomApiResponse<?>> writeReport(@Valid @RequestBody SpecReportWriteDto dto) {

        return specReportService.writeReport(dto);
    }

}
