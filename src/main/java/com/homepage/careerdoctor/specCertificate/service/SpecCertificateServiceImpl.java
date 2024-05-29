package com.homepage.careerdoctor.specCertificate.service;

import com.homepage.careerdoctor.domain.*;
import com.homepage.careerdoctor.specCertificate.dto.CertificateAllSpecDto;
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


    // 전체 유저들의 스펙 통계를 내고 해당 유저의 스펙을 진단하기
    @Transactional
    public ResponseEntity<CustomApiResponse<Object>> certificateAllSpec(Long specId, CertificateAllSpecDto dto) {
        // 1. 전체 유저들의 스펙 통계내기
        List<SpecCertificate> allSpecs = specRepository.findAll();

        // 통계 정보를 저장할 변수들
        int totalUsers = allSpecs.size();

        // 각 항목별 통계 계산을 위한 맵
        Map<String, Integer> certificateCount = new HashMap<>();
        Map<String, Integer> activityCount = new HashMap<>();
        Map<String, Integer> languageCount = new HashMap<>();
        Map<String, Integer> careerCount = new HashMap<>();
        Map<String, Integer> etcCount = new HashMap<>();

        for (SpecCertificate spec : allSpecs) {
            // 자격증 통계
            for (String certificate : spec.getCertificates()) {
                certificateCount.put(certificate, certificateCount.getOrDefault(certificate, 0) + 1);
            }
            // 대외활동 통계
            for (String activity : spec.getActivities()) {
                activityCount.put(activity, activityCount.getOrDefault(activity, 0) + 1);
            }
            // 언어 통계
            for (String language : spec.getLanguages()) {
                languageCount.put(language, languageCount.getOrDefault(language, 0) + 1);
            }
            // 경력 통계
            for (String career : spec.getCareers()) {
                careerCount.put(career, careerCount.getOrDefault(career, 0) + 1);
            }
            // 기타 통계
            for (String etc : spec.getEtcs()) {
                etcCount.put(etc, etcCount.getOrDefault(etc, 0) + 1);
            }
        }

        // 각 항목별 비율 계산
        Map<String, Double> certificatePercentage = calculatePercentage(certificateCount, totalUsers);
        Map<String, Double> activityPercentage = calculatePercentage(activityCount, totalUsers);
        Map<String, Double> languagePercentage = calculatePercentage(languageCount, totalUsers);
        Map<String, Double> careerPercentage = calculatePercentage(careerCount, totalUsers);
        Map<String, Double> etcPercentage = calculatePercentage(etcCount, totalUsers);

        // 2. 해당 유저의 스펙 진단
        Optional<SpecCertificate> userSpecOpt = specRepository.findById(specId);
        if (userSpecOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(CustomApiResponse.createFailWithoutData(HttpStatus.NOT_FOUND.value(),
                            "해당 스펙을 찾을 수 없습니다."));
        }

        SpecCertificate userSpec = userSpecOpt.get();

        // 기본 스펙 4개 분야: 자격증, 대외활동, 언어, 경력
        int satisfiedSpecs = 0;
        if (!userSpec.getCertificates().isEmpty()) satisfiedSpecs++;
        if (!userSpec.getActivities().isEmpty()) satisfiedSpecs++;
        if (!userSpec.getLanguages().isEmpty()) satisfiedSpecs++;
        if (!userSpec.getCareers().isEmpty()) satisfiedSpecs++;

        String specLevel; // 유저의 스펙 레벨
        if (satisfiedSpecs >= 4) {
            specLevel = "취뽀생";
        } else if (satisfiedSpecs == 3) {
            specLevel = "스펙우수";
        } else if (satisfiedSpecs == 2) {
            specLevel = "스펙양호";
        } else {
            specLevel = "스펙위험";
        }

        // 응답 데이터 생성
        Map<String, Object> responseData = new HashMap<>();
        responseData.put("totalUsers", totalUsers);
        responseData.put("certificatePercentage", certificatePercentage);
        responseData.put("activityPercentage", activityPercentage);
        responseData.put("languagePercentage", languagePercentage);
        responseData.put("careerPercentage", careerPercentage);
        responseData.put("etcPercentage", etcPercentage);
        responseData.put("userSpecLevel", specLevel);

        // userId 추가
        User user = userSpec.getUser();
        if (user != null) {
            responseData.put("userId", user.getUserId()); // userId 응답 데이터에 추가
        } else {
            responseData.put("userId", null); // 비회원일 경우 null
        }

        return ResponseEntity.status(HttpStatus.OK)
                .body(CustomApiResponse.createSuccess(HttpStatus.OK.value(), responseData,
                        "스펙 진단 결과를 반환했습니다."));
    }

    // 각 항목별 비율 계산 메서드
    private Map<String, Double> calculatePercentage(Map<String, Integer> countMap, int totalUsers) {
        Map<String, Double> percentageMap = new HashMap<>();
        int total = countMap.values().stream().mapToInt(Integer::intValue).sum();

        for (Map.Entry<String, Integer> entry : countMap.entrySet()) {
            double percentage = (entry.getValue() / (double) total) * 100;
            percentageMap.put(entry.getKey(), percentage);
        }

        return percentageMap;
    }
}