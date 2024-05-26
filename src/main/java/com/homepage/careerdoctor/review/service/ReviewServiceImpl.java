package com.homepage.careerdoctor.review.service;

import com.homepage.careerdoctor.domain.Review;
import com.homepage.careerdoctor.domain.SpecReport;
import com.homepage.careerdoctor.review.dto.ReviewWriteDto;
import com.homepage.careerdoctor.review.repository.ReviewRepository;
import com.homepage.careerdoctor.specReport.repository.SpecReportRepository;
import com.homepage.careerdoctor.util.response.CustomApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService{
    private final ReviewRepository reviewRepository;
    private final SpecReportRepository specReportRepository;
    @Override
    public ResponseEntity<CustomApiResponse<?>> writeReview(Long reportId, ReviewWriteDto dto) {

        // 존재하지 않는 소견서라면 후기 작성 불가
        Optional<SpecReport> foundReport = specReportRepository.findByReportId(reportId);

        if (foundReport.isEmpty()) {
            return ResponseEntity.status(400)
                    .body(CustomApiResponse.createFailWithoutData(400, "존재하지 않는 소견서입니다"));
        }

        // 존재하는 소견서라면 후기 작성 가능
        Review newReivew = Review.builder()
                .opinion(dto.getOpinion())
                .bestPoint(dto.getBestPoint())
                .reviewContent(dto.getReviewContent())
                .build();

        reviewRepository.save(newReivew);

        return ResponseEntity.status(201)
                .body(CustomApiResponse.createSuccess(201, null, "소견서 후기를 성공적으로 작성했습니다."));

    }
}
