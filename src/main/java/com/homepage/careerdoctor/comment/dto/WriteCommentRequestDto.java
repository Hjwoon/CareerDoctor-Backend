package com.homepage.careerdoctor.comment.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Builder
public class WriteCommentRequestDto {

    @NotNull(message = "댓글 작성자의 기본키를 작성해주세요.")
    private String userId;

    @NotNull(message = "게시글의 기본키를 작성해주세요.")
    private Long postId;

    @NotEmpty(message = "댓글 내용이 비어있습니다.")
    private String content;
}
