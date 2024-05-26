package com.homepage.careerdoctor.specReport.dto;

import com.homepage.careerdoctor.domain.Need;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FeedbackToMeDto {
    private Long reportId;
    private String writerId; // 소견서 써준 사람
    private String writerLevel; // 소견서 써준 사람 레벨
    private String reportTitle;
    private String reportContent;
    private List<Need> needs;
    private LocalDateTime createdAt;
}
