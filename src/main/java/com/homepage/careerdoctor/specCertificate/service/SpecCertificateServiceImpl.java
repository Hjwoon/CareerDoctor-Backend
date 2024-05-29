package com.homepage.careerdoctor.specCertificate.service;

import com.homepage.careerdoctor.domain.*;
import com.homepage.careerdoctor.specCertificate.dto.CertificateSpecDto;
import com.homepage.careerdoctor.specCertificate.repository.SpecRepository;
import com.homepage.careerdoctor.user.repository.UserRepository;
import com.homepage.careerdoctor.util.response.CustomApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@RequiredArgsConstructor
public class SpecCertificateServiceImpl implements SpecCertificateService {
    private final SpecRepository specRepository;
    private final UserRepository userRepository;

    // 스펙 진단하기
    @Transactional
    public ResponseEntity<CustomApiResponse<?>> certificateSpec(CertificateSpecDto certificateSpecDto) {

        // 요청을 처리하고 올바른 응답을 반환하는 코드

        // 사용자가 진단하기에 요청한 정보들
        String name = certificateSpecDto.getName();
        String birth = certificateSpecDto.getBirth();
        Gender gender = certificateSpecDto.getGender();
        SchoolDiv schoolDiv = certificateSpecDto.getSchoolDiv();
        String entranceYear = certificateSpecDto.getEntranceYear();
        EntranceDiv entranceDiv = certificateSpecDto.getEntranceDiv();
        GraduateDiv graduateDiv = certificateSpecDto.getGraduateDiv();
        String major = certificateSpecDto.getMajor();
        Double avgCredit = certificateSpecDto.getAvgCredit();
        List<String> certificates = certificateSpecDto.getCertificates();
        List<String> activities = certificateSpecDto.getActivities();
        List<String> languages = certificateSpecDto.getLanguages();
        List<String> careers = certificateSpecDto.getCareers();
        List<String> etcs = certificateSpecDto.getEtcs();


        SpecCertificate savedSpecCertificate;

        if (certificateSpecDto.getUserId() == null) { // 여기서 의미하는 userId는 User 테이블에 저장될 때 부여되는 Long 타입의 memberId를 의미
            // 비회원
            // userId = ""인 SpecCertificate 객체 생성 후 값 넣기

            // userId가 비어있는 SpecCertificate
            SpecCertificate createNoUserIdSpecCertificate = SpecCertificate.builder()
                    .user(null)
                    .name(name)
                    .birth(birth)
                    .gender(gender)
                    .schoolDiv(schoolDiv)
                    .entranceYear(entranceYear)
                    .entranceDiv(entranceDiv)
                    .graduateDiv(graduateDiv)
                    .major(major)
                    .avgCredit(avgCredit)
                    .certificates(certificates)
                    .activities(activities)
                    .languages(languages)
                    .careers(careers)
                    .etcs(etcs)
                    .build();
            savedSpecCertificate = specRepository.save(createNoUserIdSpecCertificate); // 비회원 스펙 저장

        } else {
            // 회원
            // userId 있는 SpecCertificate 객체 생성 후 값 넣기
            Optional<User> foundUser = userRepository.findByUserId(certificateSpecDto.getUserId()); // userID로 해당하는 회원이 있는지

            // userId가 비어있는 SpecCertificate
            SpecCertificate createUserSpecCertificate = SpecCertificate.builder()
                    // get메소드로 실제 엔티티를 가져옴. get 호출을 해야 실제 엔티티를 넣을 수 있음
                    .user(foundUser.orElse(null)) // get메소드 대신 orElse(null)을 사용하여 실제 엔티티를 가져옴
                    .name(name)
                    .birth(birth)
                    .gender(gender)
                    .schoolDiv(schoolDiv)
                    .entranceYear(entranceYear)
                    .entranceDiv(entranceDiv)
                    .graduateDiv(graduateDiv)
                    .major(major)
                    .avgCredit(avgCredit)
                    .certificates(certificates)
                    .activities(activities)
                    .languages(languages)
                    .careers(careers)
                    .etcs(etcs)
                    .build();
            savedSpecCertificate = specRepository.save(createUserSpecCertificate); // 회원 스펙 저장
        }

        // 에러 코드 400
        // 필수 필드 유효성 검사
        if (name == null || name.isEmpty() || birth == null || birth.isEmpty() || gender == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(CustomApiResponse.createFailWithoutData(HttpStatus.BAD_REQUEST.value(),
                            "이름, 생년월일, 성별은 필수항목입니다."));
        }

        // 응답 데이터 생성 // 응답 반환할 때 DTO 사용
        Map<String, Object> responseData = new HashMap<>();
        responseData.put("specId", savedSpecCertificate.getSpecId());
        responseData.put("createdAt", savedSpecCertificate.getCreatedAt().toString());

        return ResponseEntity.status(HttpStatus.OK)
                .body(CustomApiResponse.createSuccess(HttpStatus.OK.value(), responseData,
                        "스펙 진단 받기 입력을 성공했습니다."));

    }
}