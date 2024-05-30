package com.homepage.careerdoctor.specReport.dto;

import com.homepage.careerdoctor.domain.Need;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SpecReportWriteRequestDto {
    private String userId; // 피드백 받고 싶은 사람
    private String writerId; // 소견서 작성자

    @NotEmpty(message = "소견서 제목을 작성해주세요.")
    private String reportTitle;

    @NotEmpty(message = "소견서 내용을 작성해주세요.")
    @Column(columnDefinition = "TEXT") // 텍스트를 더 길게 작성할 수 있다.
    private String reportContent;

    @Size(min = 1, message = "필요한 스펙을 1개 이상 선택해주세요.")
    private List<String> needs = new ArrayList<>();


}
