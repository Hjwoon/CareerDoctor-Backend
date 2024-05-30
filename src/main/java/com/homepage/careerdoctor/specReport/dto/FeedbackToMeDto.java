package com.homepage.careerdoctor.specReport.dto;

import com.homepage.careerdoctor.domain.Need;
import com.homepage.careerdoctor.domain.NeedSpec;
import com.homepage.careerdoctor.domain.SpecLevel;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FeedbackToMeDto {
    private Long reportId;
    private String writerId; // 소견서 써준 사람
    private SpecLevel writerLevel = SpecLevel.EXCELLENT; // 소견서 써준 사람 레벨
    private String reportTitle;
    private String reportContent;
    private List<String> needs = new ArrayList<>();
    private LocalDateTime createdAt;
}
