package com.homepage.careerdoctor.review.service;

import com.homepage.careerdoctor.domain.Review;
import com.homepage.careerdoctor.domain.SpecReport;
import com.homepage.careerdoctor.domain.User;
import com.homepage.careerdoctor.review.dto.ReviewListDto;
import com.homepage.careerdoctor.review.dto.ReviewWriteRequestDto;
import com.homepage.careerdoctor.review.repository.ReviewRepository;
import com.homepage.careerdoctor.specReport.repository.SpecReportRepository;
import com.homepage.careerdoctor.user.repository.UserRepository;
import com.homepage.careerdoctor.util.response.CustomApiResponse;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Transactional
@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService{
    private final ReviewRepository reviewRepository;
    private final SpecReportRepository specReportRepository;
    private final UserRepository userRepository;

    @Override
    public ResponseEntity<CustomApiResponse<?>> writeReview(Long reportId, ReviewWriteRequestDto dto) {

        // 존재하지 않는 소견서라면 후기 작성 불가
        Optional<SpecReport> foundReport = specReportRepository.findByReportId(reportId);

        if (foundReport.isEmpty()) {
            return ResponseEntity.status(400)
                    .body(CustomApiResponse.createFailWithoutData(400, "존재하지 않는 소견서입니다"));
        }


        Long memberId = foundReport.get().getUser().getMemberId();
        Optional<User> user = userRepository.findById(memberId);

        Review review = Review.builder()
                .specReport(foundReport.get())
                .user(user.get())
                .opinion(dto.getOpinion())
                .bestPoint(dto.getBestPoint())
                .reviewContent(dto.getReviewContent())
                .build();

        reviewRepository.save(review);

        return ResponseEntity.status(201)
                .body(CustomApiResponse.createSuccess(201, null, "소견서 후기를 성공적으로 작성했습니다."));

    }

    @Override
    public ResponseEntity<CustomApiResponse<?>> getReviews() {
        List<Review> reviews = reviewRepository.findAll();
        List<ReviewListDto.ReviewResponse> reviewResponses = new ArrayList<>();

        for (Review review : reviews) {

            Long memberId = review.getUser().getMemberId();
            String userId = userRepository.findById(memberId).get().getUserId();

            reviewResponses.add(ReviewListDto.ReviewResponse.builder()
                            .userId(userId)
                            .reviewId(review.getReviewId())
                            .opinion(review.getOpinion())
                            .bestPoint(review.getBestPoint())
                            .reviewContent(review.getReviewContent())
                            .build());
        }

        return ResponseEntity.status(201)
                .body(CustomApiResponse.createSuccess(201, reviewResponses, "소견서 후기 목록을 불러오는데 성공했습니다."));

    }
}
