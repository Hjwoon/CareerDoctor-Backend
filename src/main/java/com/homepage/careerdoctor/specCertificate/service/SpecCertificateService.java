package com.homepage.careerdoctor.specCertificate.service;

import com.homepage.careerdoctor.specCertificate.dto.CertificateSpecDto;
import com.homepage.careerdoctor.util.response.CustomApiResponse;
import org.springframework.http.ResponseEntity;

public interface SpecCertificateService {
    public ResponseEntity<CustomApiResponse<?>> certificateSpec(CertificateSpecDto dto); // 스펙 작성하기
    public ResponseEntity<CustomApiResponse<Object>> certificateAllSpec(String userId); // 전체 스펙 통계내기

}
