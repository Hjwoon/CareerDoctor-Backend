package com.homepage.careerdoctor.domain;

import com.homepage.careerdoctor.util.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "SPEC_REPORT")
public class SpecReport extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "REPORT_ID")
    private Long reportId;

    @Column(name = "REPORT_TITLE")
    private String reportTitle;

    @Column(name = "REPORT_CONTENT")
    private String reportContent;


    @OneToMany(mappedBy = "specReport")
    private List<Review> reviews;

    @OneToMany(mappedBy = "specReport")
    private List<Need> needs;

    // 추가: User와의 관계 정의
    @ManyToOne
    @JoinColumn(name = "MEMBER_ID")
    private User user;
}
