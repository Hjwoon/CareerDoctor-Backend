package com.homepage.careerdoctor.comment.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter @Setter
@Builder
public class WriteCommentResponseDto {
    private Long commentId;
    private LocalDateTime updatedAt;
}
