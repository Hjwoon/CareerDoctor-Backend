package com.homepage.careerdoctor.specReport.dto;


import com.homepage.careerdoctor.domain.NeedSpec;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NeedDto {
    @NotNull(message = "필요한 스펙을 선택해주세요.")
    private NeedSpec needSpec;
}
