package com.homepage.careerdoctor.specCertificate.dto;

import com.homepage.careerdoctor.domain.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CertificateSpecDto {
    private String userId;

    // 필수 항목 세 가지: 이름, 생년월일, 성별
    @NotEmpty(message = "이름을 입력해주세요.")
    private String name;

    @NotEmpty(message = "생년월일을 입력해주세요.")
    private String birth;

    @NotNull(message = "성별을 입력해주세요.") // @NotNull은 모든 타입에서 사용 가능
    private Gender gender;

    // 학력
    @NotNull(message = "학력을 입력해주세요.")
    private SchoolDiv schoolDiv;

    // 입학년월
    private String entranceYear;

    // 입학 구분
    private EntranceDiv entranceDiv;

    // 졸업(예정)년월
    private GraduateDiv graduateDiv;

    // 전공
    private String major;

    // 학점
    private Double avgCredit;

    // 보유 자격증
    private List<String> certificates;

    // 대외활동
    private List<String> activities;

    // 언어
    private List<String> languages;

    // 경력
    private List<String> careers;

    // 기타
    private List<String> etcs;
}