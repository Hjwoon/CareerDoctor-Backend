package com.homepage.careerdoctor.comment.controller;

import com.homepage.careerdoctor.comment.dto.WriteCommentRequestDto;
import com.homepage.careerdoctor.comment.service.CommentService;
import com.homepage.careerdoctor.util.entity.BaseEntity;
import com.homepage.careerdoctor.util.response.CustomApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("api/careerdoctor")
@RequiredArgsConstructor
public class CommentController extends BaseEntity {
    private final CommentService commentService;

    // 1. 댓글 작성
    // [POST] api/comments
    @PostMapping("/comments")
    public ResponseEntity<CustomApiResponse<?>> writeComment(
            @RequestBody WriteCommentRequestDto dto) {
        System.out.println("#####################");
        return commentService.writeComment(dto);
    }

    // 2. 댓글 수정
    // [PUT] api/comments
    /*@PutMapping
    public ResponseEntity<CustomApiResponse<?>> modifyComment(
            @RequestBody @Valid ModifyCommentRequestDto dto) {
        return commentService.modifyComment(dto);
    }*/

    // 3. 댓글 삭제
    // [DELETE] api/comments
    /*@DeleteMapping
    public ResponseEntity<CustomApiResponse<?>> deleteComment(
            @RequestBody @Valid DeleteCommentRequestDto dto) {
        return commentService.deleteComment(dto);
    }*/

    // 4. 댓글 조회
}
