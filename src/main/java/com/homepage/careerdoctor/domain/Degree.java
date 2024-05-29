package com.homepage.careerdoctor.domain;

import com.homepage.careerdoctor.util.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "DEGREE")
public class Degree extends BaseEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "DEGREE_ID")
    private Long degreeId;

    @Enumerated(EnumType.STRING)
    private SchoolDiv schoolDiv;

    @Column(name = "SCHOOL_NAME")
    private String schoolName;

    @Column(name = "ENTRANCE_YEAR")
    private String entranceYear;

    @Enumerated(EnumType.STRING)
    private EntranceDiv entranceDiv;

    @Column(name = "GRADUATE_YEAR")
    private String graduateYear;

    @Enumerated(EnumType.STRING)
    private GraduateDiv graduateDiv;

    @Column(name = "MAJOR")
    private String major;

    @Column(name = "CREDIT")
    private BigDecimal credit;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "SPEC_ID")
    private SpecCertificate specCertificate;
}
