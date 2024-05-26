package com.homepage.careerdoctor.domain;

import com.homepage.careerdoctor.util.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "SPEC_REPORTS")
public class SpecReport extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "report_id")
    private Long reportId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private User user;

    @Column(name = "report_title")
    private String reportTitle;

    @Column(name = "report_content")
    private String reportContent;

    @OneToOne(mappedBy = "specReport", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Review review;

    @OneToOne(mappedBy = "specReport", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Need need;
}
