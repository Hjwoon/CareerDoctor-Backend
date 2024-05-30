package com.homepage.careerdoctor.comment.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Builder
public class DeleteCommentRequestDto {
    @NotNull(message = "회원 기본키 필요")
    private String userId; // 누가 삭제하려는지 알아야함

    @NotNull(message = "댓글 기본키 필요")
    private Long commentId; // 어떤 댓글을 삭제하려는지 알아야함
}
