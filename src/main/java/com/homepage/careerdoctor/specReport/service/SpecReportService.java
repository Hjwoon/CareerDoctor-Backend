package com.homepage.careerdoctor.specReport.service;

import com.homepage.careerdoctor.specReport.dto.SpecReportWriteRequestDto;
import com.homepage.careerdoctor.util.response.CustomApiResponse;
import org.springframework.http.ResponseEntity;

public interface SpecReportService {
    public ResponseEntity<CustomApiResponse<?>> writeReport(Long specId, SpecReportWriteRequestDto dto); // 소견서 작성
    public ResponseEntity<CustomApiResponse<?>> getReports(); // 소견서 조회

    ResponseEntity<CustomApiResponse<?>> getAllMyReport(String userId);

    ResponseEntity<CustomApiResponse<?>> getMyReport(String userId, Long reportId);
}
