package com.homepage.careerdoctor.specReport.service;


import com.homepage.careerdoctor.specReport.dto.SpecReportWriteDto;
import com.homepage.careerdoctor.specReport.repository.SpecReportRepository;
import com.homepage.careerdoctor.util.response.CustomApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SpecReportServiceImpl implements SpecReportService{

    private final SpecReportRepository specReportRepository;
    @Override
    public ResponseEntity<CustomApiResponse<?>> writeReport(SpecReportWriteDto dto) {
        // 존재하지 않는 진단서라면 실패 반환하는 로직 추가



//        // 존재하는 진단서라면 소견서 작성
//        SpecReport newReport = SpecReport.builder()
//                .reportTitle(dto.getReportTitle())
//                .reportContent(dto.getReportContent())
//                .needs(dto.getNeedSpecs())
//                .build();
//
//        specReportRepository.save(newReport); // 새 소견서 저장

        return ResponseEntity.status(201)
                .body(CustomApiResponse.createSuccess(201, null, "소견서를 성공적으로 작성했습니다."));
    }
}
