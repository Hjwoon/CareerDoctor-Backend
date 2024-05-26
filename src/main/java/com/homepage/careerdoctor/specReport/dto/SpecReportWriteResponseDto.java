package com.homepage.careerdoctor.specReport.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class SpecReportWriteResponseDto {
    private Long reportId;
    private LocalDateTime createdAt;
}
