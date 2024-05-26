package com.homepage.careerdoctor.specCertificate.service;

import com.homepage.careerdoctor.specCertificate.dto.SertificateSpecDto;
import com.homepage.careerdoctor.util.response.CustomApiResponse;
import org.springframework.http.ResponseEntity;

public interface SpecCertificateService {
    public ResponseEntity<CustomApiResponse<?>> sertificateSpec(SertificateSpecDto dto); // 스펙 작성하기
}
