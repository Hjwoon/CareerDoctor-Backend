package com.homepage.careerdoctor.review.service;

import com.homepage.careerdoctor.review.dto.ReviewWriteRequestDto;
import com.homepage.careerdoctor.util.response.CustomApiResponse;
import org.springframework.http.ResponseEntity;

public interface ReviewService {
    public ResponseEntity<CustomApiResponse<?>> writeReview(Long reprotId, ReviewWriteRequestDto dto);

    public ResponseEntity<CustomApiResponse<?>> getReviews();
}
