package com.homepage.careerdoctor.specReport.dto;

import com.homepage.careerdoctor.domain.Need;
import com.homepage.careerdoctor.domain.SpecLevel;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


public class SpecReportListDto {

    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ReportResponse {
        @NotEmpty
        private Long reportId;
        private String writerId; // 소견서 써준 사람
        private SpecLevel level;
        private String reportTitle;
        private String reportContent;
        private List<String> needs = new ArrayList<>();
        private LocalDateTime createdAt;


    }

    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    public static class SearchReports {
        private List<ReportResponse> reports;

        public SearchReports(List<ReportResponse> reports) {
            this.reports = reports;
        }
    }

}
