package com.homepage.careerdoctor.specReport.repository;

import com.homepage.careerdoctor.domain.Review;
import com.homepage.careerdoctor.domain.SpecReport;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SpecReportRepository extends JpaRepository<SpecReport, Long> {

    Optional<SpecReport> findByReportId(Long reportId);
}
