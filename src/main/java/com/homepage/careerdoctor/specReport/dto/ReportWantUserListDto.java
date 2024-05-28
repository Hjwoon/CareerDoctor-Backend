package com.homepage.careerdoctor.specReport.dto;

import com.homepage.careerdoctor.domain.Gender;
import com.homepage.careerdoctor.domain.SpecLevel;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReportWantUserListDto {
    @NotEmpty
    private String userId; // 소견서 작성 원하는 사람
    private String birth;
    private SpecLevel level;
    private Gender gender;
}
