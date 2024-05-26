package com.homepage.careerdoctor.review.repository;

import com.homepage.careerdoctor.domain.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    Optional<Review> findByReviewId(Long reviewId); // 소견서 아이디를 사용해 리뷰 찾기
}
