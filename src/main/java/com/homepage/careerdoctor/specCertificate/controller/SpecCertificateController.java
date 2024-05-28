package com.homepage.careerdoctor.specCertificate.controller;

import com.homepage.careerdoctor.specCertificate.dto.CertificateSpecDto;
import com.homepage.careerdoctor.specCertificate.service.SpecCertificateServiceImpl;
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
public class SpecCertificateController {
    private final SpecCertificateServiceImpl specCertificateService;

    // 스펙 작성하기
    @PostMapping("/write-spec")
    private ResponseEntity<CustomApiResponse<?>> certificateSpec(@Valid @RequestBody CertificateSpecDto dto) {
        return specCertificateService.certificateSpec(dto);
    }
}
