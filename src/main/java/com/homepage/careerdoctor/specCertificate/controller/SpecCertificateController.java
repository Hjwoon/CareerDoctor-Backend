package com.homepage.careerdoctor.specCertificate.controller;

import com.homepage.careerdoctor.specCertificate.dto.CertificateAllSpecDto;
import com.homepage.careerdoctor.specCertificate.dto.CertificateSpecDto;
import com.homepage.careerdoctor.specCertificate.service.SpecCertificateServiceImpl;
import com.homepage.careerdoctor.util.response.CustomApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    // 스펙 진단하기
    @GetMapping("/view-spec/{specId}")
    public ResponseEntity<CustomApiResponse<Object>> showUsersSpec(@PathVariable Long specId, CertificateAllSpecDto dto) {
        return specCertificateService.certificateAllSpec(specId, dto);
    }
}
