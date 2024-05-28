package com.homepage.careerdoctor.review.dto;

import com.homepage.careerdoctor.domain.BestPoint;
import com.homepage.careerdoctor.domain.OPINION;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReviewWriteRequestDto {

    @NotNull(message = "소견서가 도움이 되었는지 선택해주세요.")
    private OPINION opinion;

    @NotNull(message = "어떤 부분이 가장 도움이 되었는지 선택해주세요.")
    private BestPoint bestPoint;

    @NotEmpty(message = "소견서 후기를 작성해주세요.")
    @Column(columnDefinition = "TEXT") // 텍스트를 더 길게 작성할 수 있다.
    private String reviewContent;



}
