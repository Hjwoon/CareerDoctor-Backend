package com.homepage.careerdoctor.specCertificate.service;

import com.homepage.careerdoctor.domain.SpecCertificate;
import com.homepage.careerdoctor.specCertificate.dto.CertificateSpecDto;
import com.homepage.careerdoctor.specCertificate.repository.SpecRepository;
import com.homepage.careerdoctor.util.response.CustomApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SpecCertificateServiceImpl implements SpecCertificateService {
    private final SpecRepository specRepository;

    // 스펙 진단하기
    @Override
    public ResponseEntity<CustomApiResponse<?>> certificateSpec(CertificateSpecDto certificateSpecDto) {

        //Optional<SpecCertificate> foundSpec = specRepository.findByUserId(certificateSpecDto.getUserId());

        // userId가 ""(null)값이 아니라면, 회원

        // 로그인된 상태 -> 내 스펙에 저장 // userId를 DB에 존재하는지 확인
        // 1. 사용자가 스펙 진단하기에 요청한 아이디가 DB에 존재하는지 확인


        //userId가 ""(null, 프론트에서 requestBody의 userId로 넘겨준 값)값이라면, 비회원
        // 로그인 안 된 상태 -> 진단하기 후 유저 아이디가 없는 스펙 DB에 저장

        return ResponseEntity.status(201)
                .body(CustomApiResponse.createSuccess(201, null, "소견서 진단을 성공적으로 작성했습니다."));


    }
}
