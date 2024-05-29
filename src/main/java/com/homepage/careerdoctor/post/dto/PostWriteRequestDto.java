package com.homepage.careerdoctor.post.dto;

import com.homepage.careerdoctor.domain.Post;
import com.homepage.careerdoctor.domain.Vote;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PostWriteRequestDto {

    @NotNull(message = "회원 아이디는 비워져 있을 수 없습니다.")
    private String userId; // 게시글 작성자 아이디
    @NotBlank(message = "제목을 입력하세요.")
    private String postTitle;
    @NotBlank(message = "내용을 입력하세요.")
    private String postContent;
    private List<VoteDto> vote = new ArrayList<>();

}
