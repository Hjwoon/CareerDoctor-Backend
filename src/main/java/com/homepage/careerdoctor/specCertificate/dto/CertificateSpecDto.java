package com.homepage.careerdoctor.specCertificate.dto;

import com.homepage.careerdoctor.domain.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CertificateSpecDto {
    private String userId;

    @NotEmpty(message = "생일을 입력해주세요.")
    private String birth;

    @NotEmpty(message = "성별을 입력해주세요.")
    private Gender gender;

    @NotEmpty(message = "학력을 입력해주세요.")
    private SchoolDiv schoolDiv;

    @NotEmpty(message = "입학년월을 입력해주세요.")
    private String entranceYear;

    @NotEmpty(message = "입학 구분을 입력해주세요.")
    private EntranceDiv entranceDiv;

    @NotEmpty(message = "졸업(예정)년월을 입력해주세요.")
    private GraduateDiv graduateDiv;

    @NotEmpty(message = "전공을 입력해주세요.")
    private String major;

    @NotEmpty(message = "학점을 입력해주세요.")
    private Double avgCredit;

    @NotEmpty(message = "보유 자격증을 입력해주세요.")
    private ArrayList<String> certificate;
    private String certificateName;

    @NotEmpty(message = "대외활동을 입력해주세요.")
    private ArrayList<String> activity;
    private String activityName;

    @NotEmpty(message = "언어를 입력해주세요.")
    private ArrayList<String> language;

    @NotEmpty(message = "경력을 입력해주세요.")
    private ArrayList<Career> careers;

    @NotEmpty(message = "기타를 입력해주세요.")
    private String etc;
}
