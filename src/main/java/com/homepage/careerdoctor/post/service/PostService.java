package com.homepage.careerdoctor.post.service;

import com.homepage.careerdoctor.post.dto.PostDeleteRequestDto;
import com.homepage.careerdoctor.post.dto.PostModifyRequestDto;
import com.homepage.careerdoctor.post.dto.PostWriteRequestDto;
import com.homepage.careerdoctor.util.response.CustomApiResponse;
import org.springframework.http.ResponseEntity;

public interface PostService {
    ResponseEntity<CustomApiResponse<?>> writePost(PostWriteRequestDto requestDto);

    ResponseEntity<CustomApiResponse<?>> getPosts();

    ResponseEntity<CustomApiResponse<?>> getMyPost(String userId);

    ResponseEntity<CustomApiResponse<?>> modifyPost(String userId, Long postId, PostModifyRequestDto.Req req);

    ResponseEntity<CustomApiResponse<?>> deletePost(PostDeleteRequestDto dto);

    ResponseEntity<CustomApiResponse<?>> getPostDeatail(Long postId);

    ResponseEntity<CustomApiResponse<?>> plusVoteCount(Long voteId);
}
