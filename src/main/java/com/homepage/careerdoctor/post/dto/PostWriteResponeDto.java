package com.homepage.careerdoctor.post.dto;

import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostWriteResponeDto {
    @NotNull(message = "게시글 기본키는 값이 비워져 있을 수 없습니다.")
    private Long postId;
    private LocalDateTime createdAt;
}
