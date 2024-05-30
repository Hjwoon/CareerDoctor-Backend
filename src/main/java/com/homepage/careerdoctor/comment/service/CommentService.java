package com.homepage.careerdoctor.comment.service;

import com.homepage.careerdoctor.comment.dto.*;
import com.homepage.careerdoctor.comment.repository.CommentRepository;
import com.homepage.careerdoctor.domain.Comment;
import com.homepage.careerdoctor.domain.User;
import com.homepage.careerdoctor.domain.Post;
import com.homepage.careerdoctor.user.repository.UserRepository;
import com.homepage.careerdoctor.post.repostitory.PostRepository;
import com.homepage.careerdoctor.util.response.CustomApiResponse;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final PostRepository postRepository;

    // 1. 댓글 작성
    @Transactional
    public ResponseEntity<CustomApiResponse<?>> writeComment(
            WriteCommentRequestDto dto) {
        // 1.1 댓글 작성자가 DB에 존재하는지 확인
        Optional<User> foundUser = userRepository.findByUserId(dto.getUserId());
        if(foundUser.isEmpty()) {
            return ResponseEntity
                    .status(HttpStatus.FORBIDDEN) // 존재하지 않는 접근
                    .body(CustomApiResponse.createFailWithoutData(HttpStatus.FORBIDDEN.value(),
                            "잘못된 접근입니다."));
        }

        // 1.2 게시글이 실제로 존재하는지 확인
        Optional<Post> foundPost = postRepository.findById(dto.getPostId());
        // 게시글이 존재하지 않는다면
        if(foundPost.isEmpty()) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(CustomApiResponse.createFailWithoutData(HttpStatus.NOT_FOUND.value(),
                            "게시글이 삭제되었거나 존재하지 않습니다."));
        }

        // 1.3 댓글 생성 및 연관관계 설정
        Comment createdComment = Comment.builder().content(dto.getContent()).build();
        createdComment.createComment(foundUser.get(), foundPost.get()); // .get()으로 접근해야 비로소 user, post가 됨

        commentRepository.save(createdComment); // DB에 저장

        // 1.4 사용자에게 반환할 데이터(dto) 만들기
        WriteCommentResponseDto responseDto = WriteCommentResponseDto.builder()
                .commentId(createdComment.getCommentId())
                .updatedAt(createdComment.getCreatedAt())
                .build();

        // 응답 보내기
        return ResponseEntity.status(HttpStatus.OK)
                .body(CustomApiResponse.createSuccess(HttpStatus.OK.value(), responseDto,
                        "댓글이 작성되었습니다."));

    }

    // 2. 댓글 수정
    @Transactional
    public ResponseEntity<CustomApiResponse<?>> modifyComment(ModifyCommentRequestDto dto) {
        // 2.1 수정하려는 댓글이 존재하는지 확인
        Optional<Comment> foundComment = commentRepository.findById(dto.getCommentId());

        if(foundComment.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(CustomApiResponse.createFailWithoutData(HttpStatus.NOT_EXTENDED.value(), "댓글이 존재하지 않습니다."));
        }

        // 2.2 댓글 작성자와 현재 로그인한 사용자가 같은지 확인
        Long commentedUserId = foundComment.get().getUser().getMemberId(); // 댓글 단 사람의 ID
        if(commentedUserId != dto.getCommentId()) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                    .body(CustomApiResponse.createFailWithoutData(HttpStatus.FORBIDDEN.value(), "잘못된 접근입니다."));
        }

        // 2.3 댓글 수정
        Comment comment = foundComment.get();
        comment.changeContent(dto.getContent());

        // 2.4 응답 생성
        ModifyCommentResponseDto responseDto = ModifyCommentResponseDto.builder()
                .updatedAt(comment.getUpdatedAt())
                .build();

        return ResponseEntity.status(HttpStatus.OK)
                .body(CustomApiResponse.createSuccess(HttpStatus.OK.value(), responseDto, "댓글이 수정되었습니다."));
    }


    // 3. 댓글 삭제
    /*@Transactional
    public ResponseEntity<CustomApiResponse<?>> deleteComment(DeleteCommentRequestDto dto) {
        // 3.1 댓글이 존재하는지 확인
        Optional<Comment> foundComment = commentRepository.findById(dto.getCommentsId());
        if(foundComment.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(CustomApiResponse.createFailWithoutData(HttpStatus.NO_CONTENT.value(),
                            "댓글이 존재하지 않습니다."));
        }

        // 3.2 댓글 작성자와 현재 로그인한 사용자가 같은지 확인
        Comment comment = foundComment.get();
        if(comment.getUser().getMemberId() != dto.getUserId()) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                    .body(CustomApiResponse.createFailWithoutData(HttpStatus.FORBIDDEN.value(),
                            "잘못된 요청입니다."));
        }

        // 3.3 위 조건들 통과시 삭제
        commentRepository.delete(comment);

        return ResponseEntity.status(HttpStatus.OK)
                .body(CustomApiResponse.createSuccess(HttpStatus.OK.value(),
                        null, "댓글이 삭제되었습니다."));

    }*/

    // 4. 댓글 조회

}
