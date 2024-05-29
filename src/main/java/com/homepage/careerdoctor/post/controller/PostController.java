package com.homepage.careerdoctor.post.controller;

import com.homepage.careerdoctor.post.dto.PostDeleteRequestDto;
import com.homepage.careerdoctor.post.dto.PostModifyRequestDto;
import com.homepage.careerdoctor.post.dto.PostWriteRequestDto;
import com.homepage.careerdoctor.post.service.PostServiceImpl;
import com.homepage.careerdoctor.util.response.CustomApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/careerdoctor")
@RequiredArgsConstructor
public class PostController {

    private final PostServiceImpl postService;

    @PostMapping("/write-post")
    private ResponseEntity<CustomApiResponse<?>> writePost(@RequestBody PostWriteRequestDto dto) {
        return postService.writePost(dto);
    }

    @GetMapping("/posts")
    private ResponseEntity<CustomApiResponse<?>> getPosts() {
        return postService.getPosts();
    }

    @GetMapping("/posts/{postId}")
    private ResponseEntity<CustomApiResponse<?>> getPostDetail(@PathVariable Long postId) {
        return postService.getPostDeatail(postId);}

    @GetMapping("/{userId}/myposts")
    private ResponseEntity<CustomApiResponse<?>> getMyPost(@PathVariable String userId) {
        return postService.getMyPost(userId);
    }

    @PutMapping("/posts/{userId}/{postId}")
    private ResponseEntity<CustomApiResponse<?>> modifyPost(@PathVariable String userId, @PathVariable Long postId, @RequestBody PostModifyRequestDto.Req req) {
        return postService.modifyPost(userId, postId, req);
    }

    @PutMapping("/plusVoteCount/{voteId}")
    private ResponseEntity<CustomApiResponse<?>> plusVoteCount(@PathVariable Long voteId) {
        return postService.plusVoteCount(voteId);
    }

    @DeleteMapping("/posts")
    private ResponseEntity<CustomApiResponse<?>> deletePost(@RequestBody @Valid PostDeleteRequestDto dto) {
        return postService.deletePost(dto);
    }
}
