package com.homepage.careerdoctor.post.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostDeleteRequestDto {
    @NotBlank(message = "아이디가 필요합니다.")
    private String userId;
    @NotNull(message = "게시물 기본키가 필요합니다.")
    private Long postId;
}
