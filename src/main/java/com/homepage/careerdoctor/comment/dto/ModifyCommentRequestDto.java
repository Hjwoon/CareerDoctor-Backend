package com.homepage.careerdoctor.comment.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Builder
public class ModifyCommentRequestDto {
    @NotNull(message = "회원 기본키 필요")
    private String userId;

    @NotNull(message = "댓글 기본키 필요")
    private Long commentId;

    @NotEmpty(message = "댓글 내용 작성 필요")
    private String content;


}
