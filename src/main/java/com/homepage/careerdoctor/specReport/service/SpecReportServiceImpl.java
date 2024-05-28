package com.homepage.careerdoctor.specReport.service;


import com.homepage.careerdoctor.domain.*;
import com.homepage.careerdoctor.review.repository.ReviewRepository;
import com.homepage.careerdoctor.specCertificate.repository.SpecRepository;
import com.homepage.careerdoctor.specReport.dto.FeedbackToMeDto;
import com.homepage.careerdoctor.specReport.dto.ReportWantUserListDto;
import com.homepage.careerdoctor.specReport.dto.SpecReportWriteRequestDto;
import com.homepage.careerdoctor.specReport.dto.SpecReportWriteResponseDto;
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
import java.util.stream.Collectors;

@Transactional
@Service
@RequiredArgsConstructor
public class SpecReportServiceImpl implements SpecReportService{

    private final SpecReportRepository specReportRepository;
    private final UserRepository userRepository;
    private final SpecRepository specRepository;
    private final ReviewRepository reviewRepository;


    // 소견서 작성
    @Override
    public ResponseEntity<CustomApiResponse<?>> writeReport(Long specId, SpecReportWriteRequestDto dto) {
        // 존재하지 않는 진단서라면 실패 반환하는 로직 추가
        Optional<SpecCertificate> findCertificate = specRepository.findBySpecId(specId);

        if (findCertificate.isEmpty()) {
            return ResponseEntity.status(201)
                    .body(CustomApiResponse.createSuccess(400, null, "존재하지 않는 스펙 진단서입니다."));
        }

        User user = findCertificate.get().getUser();

        // 존재하는 진단서라면 소견서 작성
        SpecReport newReport = SpecReport.builder()
                .user(user)
                .writerId(dto.getWriterId())
                .reportTitle(dto.getReportTitle())
                .reportContent(dto.getReportContent())
                .build();

        // Need 객체를 생성하고 SpecReport와 연관을 설정
        List<Need> needs = dto.getNeeds().stream()
                .map(needDto -> Need.builder()
                        .specReport(newReport)
                        .needSpec(needDto.getNeedSpec())
                        .build())
                .collect(Collectors.toList());

        newReport.changeNeed(needs); // SpecReport에 Need 리스트를 설정

        specReportRepository.save(newReport); // 새 소견서 저장

        SpecReportWriteResponseDto data = SpecReportWriteResponseDto.builder()
                .reportId(newReport.getReportId())
                .createdAt(newReport.getCreatedAt())
                .build();

        return ResponseEntity.status(201)
                .body(CustomApiResponse.createSuccess(201, data, "소견서를 성공적으로 작성했습니다."));
    }

    // 소견서 원하는 사람들 목록 보기
    // 리스판스 수정
    @Override
    public ResponseEntity<CustomApiResponse<?>> getReports() {

        List<SpecCertificate> specCertificates = specRepository.findAll();
        List<ReportWantUserListDto> userResponses = new ArrayList<>();

        for (SpecCertificate specCertificate : specCertificates) {

            Long memberId = specCertificate.getUser().getMemberId();
            String userId = userRepository.findById(memberId).get().getUserId();

            userResponses.add(ReportWantUserListDto.builder()
                            .userId(userId)
                            .birth(specCertificate.getBirth())
                            .gender(specCertificate.getGender())
                            .level(specCertificate.getUser().getSpecLevel())
                            .build());
        }

        return ResponseEntity.status(201)
                .body(CustomApiResponse
                        .createSuccess(201, userResponses, "피드백을 원하는 유저 목록을 불러오는데 성공했습니다."));
    }

    // 나를 피드백 해준 사람들 목록 상세 보기
    @Override
    public ResponseEntity<CustomApiResponse<?>> getAllMyReport(String userId) {

        // 현재 회원이 누구인지 DB에서 찾는다.
        Optional<User> findUser = userRepository.findByUserId(userId);
        // 전체 소견서를 불러온다.
        List<SpecReport> reports = specReportRepository.findAll();
        // 전체 후기를 불러온다.
        List<Review> reviews = reviewRepository.findAll();

        // data로 반환할 DTO
        FeedbackToMeDto feedbackList = new FeedbackToMeDto();

        // 후기의 유저 아이디와 현재 유저의 아이디가 같으면 그 아이디를 이용해 소견서 아이디를 찾는다.
        // 찾은 소견서 아이디로 내게 피드백 해준 사람들을 찾는다.
        for (int i = 0; i < reviews.size(); i++) {
            if (reviews.get(i).getUser().getUserId() == findUser.get().getUserId()) {
                Long myReportId = reviews.get(i).getSpecReport().getReportId();
                Optional<SpecReport> myReport = specReportRepository.findByReportId(myReportId);

                feedbackList.setReportId(myReportId);
                feedbackList.setReportTitle(myReport.get().getReportTitle());
                feedbackList.setReportContent(myReport.get().getReportContent());
                feedbackList.setNeeds(myReport.get().getNeeds());
                feedbackList.setCreatedAt(myReport.get().getCreatedAt());
            }
        }

        return ResponseEntity.status(201)
                .body(CustomApiResponse
                        .createSuccess(201, feedbackList, "나에게 피드백을 해준 유저 목록을 불러오는데 성공했습니다."));
    }

    // 피드백 해준 소견서 상세보기
    @Override
    public ResponseEntity<CustomApiResponse<?>> getMyReport(String userId, Long reportId) {
        // 현재 회원이 누구인지 DB에서 찾는다.
        Optional<User> findUser = userRepository.findByUserId(userId);
        // 상세를 보고 싶은 소견서를 찾는다.
        Optional<SpecReport> findReport = specReportRepository.findByReportId(reportId);
        // data로 반환할 DTO
        FeedbackToMeDto feedbackList = new FeedbackToMeDto();

        // 소견서에서 값을 찾아 dto를 이용해 response Body로 전달한다.
        feedbackList.setReportTitle(findReport.get().getReportTitle());
        feedbackList.setReportContent(findReport.get().getReportContent());
        feedbackList.setCreatedAt(findReport.get().getCreatedAt());

        return ResponseEntity.status(201)
                .body(CustomApiResponse
                        .createSuccess(201, feedbackList, "피드백 받은 소견서 하나를 보는데 성공했습니다."));

    }
}
