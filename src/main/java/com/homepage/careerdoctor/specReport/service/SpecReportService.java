package com.homepage.careerdoctor.specReport.service;

import com.homepage.careerdoctor.specReport.dto.SpecReportWriteDto;
import com.homepage.careerdoctor.util.response.CustomApiResponse;
import org.springframework.http.ResponseEntity;

public interface SpecReportService {
    public ResponseEntity<CustomApiResponse<?>> writeReport(SpecReportWriteDto dto); // 소견서 작성
}
