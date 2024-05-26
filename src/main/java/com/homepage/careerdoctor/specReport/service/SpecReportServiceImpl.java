package com.homepage.careerdoctor.specReport.service;


import com.homepage.careerdoctor.domain.SpecReport;
import com.homepage.careerdoctor.domain.User;
import com.homepage.careerdoctor.specReport.dto.SpecReportListDto;
import com.homepage.careerdoctor.specReport.dto.SpecReportWriteRequestDto;
import com.homepage.careerdoctor.specReport.dto.SpecReportWriteResponseDto;
import com.homepage.careerdoctor.specReport.repository.SpecReportRepository;
import com.homepage.careerdoctor.user.repository.UserRepository;
import com.homepage.careerdoctor.util.response.CustomApiResponse;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Transactional
@Service
@RequiredArgsConstructor
public class SpecReportServiceImpl implements SpecReportService{

    private final SpecReportRepository specReportRepository;
    private final UserRepository userRepository;


    // 소견서 작성
    @Override
    public ResponseEntity<CustomApiResponse<?>> writeReport(Long specId, SpecReportWriteRequestDto dto) {
        // 존재하지 않는 진단서라면 실패 반환하는 로직 추가


        // 존재하는 진단서라면 소견서 작성
        SpecReport newReport = SpecReport.builder()
                .reportTitle(dto.getReportTitle())
                .reportContent(dto.getReportContent())
                .needs(dto.getNeeds())
                .build();

        specReportRepository.save(newReport); // 새 소견서 저장

        SpecReportWriteResponseDto data = SpecReportWriteResponseDto.builder()
                .reportId(newReport.getReportId())
                .createdAt(newReport.getCreatedAt())
                .build();

        return ResponseEntity.status(201)
                .body(CustomApiResponse.createSuccess(201, data, "소견서를 성공적으로 작성했습니다."));
    }

    // 소견서 목록 보기
    @Override
    public ResponseEntity<CustomApiResponse<?>> getReports(String type) {
        List<SpecReport> reports = specReportRepository.findAll();
        List<SpecReportListDto.ReportResponse> reportResponses = new ArrayList<>();

        if (type.equals("all")) { // 전체 조회
            for (SpecReport report : reports) {
                reportResponses.add(SpecReportListDto.ReportResponse.builder()
                                .reportId(report.getReportId())
                                .writerId(report.getUser().getUserId())
                                .level(report.getUser().getLevel())
                                .reportTitle(report.getReportTitle())
                                .reportContent(report.getReportContent())
                                .needs(report.getNeeds())
                                .createdAt(report.getCreatedAt())
                                .build());
            }
        } else {
            int limit = Math.min(6, reports.size());
            for (int i = 0; i < limit; i++) {
                SpecReport report = reports.get(i);
                reportResponses.add(SpecReportListDto.ReportResponse.builder()
                        .reportId(report.getReportId())
                        .writerId(report.getUser().getUserId())
                        .level(report.getUser().getLevel())
                        .reportTitle(report.getReportTitle())
                        .reportContent(report.getReportContent())
                        .needs(report.getNeeds())
                        .createdAt(report.getCreatedAt())
                        .build());
            }
        }


        return ResponseEntity.status(201)
                .body(CustomApiResponse.createSuccess(201, reportResponses, "소견서 목록을 성공적으로 작성했습니다."));
    }

    @Override
    public ResponseEntity<CustomApiResponse<?>> getMyReport(String userId) {
        //List<SpecReport> findReport = specReportRepository.findAllById()
        return null;
    }

    @Override
    public ResponseEntity<CustomApiResponse<?>> getMyReports(String userId, Long reportId) {
        return null;
    }
}
