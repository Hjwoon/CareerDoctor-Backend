package com.homepage.careerdoctor.specReport.dto;

import com.homepage.careerdoctor.domain.Need;
import com.homepage.careerdoctor.domain.NeedSpec;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SpecReportWriteDto {
    @NotEmpty(message = "소견서 제목을 작성해주세요.")
    private String reportTitle;

    @NotEmpty(message = "소견서 내용을 작성해주세요.")
    @Column(columnDefinition = "TEXT") // 텍스트를 더 길게 작성할 수 있다.
    private String reportContent;

    @Size(min = 1, message = "필요한 스펙을 1개 이상 선택해주세요.")
    private List<NeedSpec> needSpecs;

//    public Need toEntity() {
//        return Need.builder()
//                .needName(needSpecs)
//                .build();
//    }

}
