package com.homepage.careerdoctor.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.homepage.careerdoctor.util.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "NEED")
public class Need extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "NEED_ID")
    private Long needId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "REPORT_ID")
    @JsonBackReference
    private SpecReport specReport;

    @Enumerated(EnumType.STRING)
    private NeedSpec needSpec;
}
