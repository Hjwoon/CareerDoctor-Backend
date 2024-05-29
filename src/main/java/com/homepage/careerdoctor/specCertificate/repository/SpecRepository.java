package com.homepage.careerdoctor.specCertificate.repository;

import com.homepage.careerdoctor.domain.SpecCertificate;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SpecRepository extends JpaRepository<SpecCertificate, Long> {
    Optional<SpecCertificate> findBySpecId(Long specId);
}
