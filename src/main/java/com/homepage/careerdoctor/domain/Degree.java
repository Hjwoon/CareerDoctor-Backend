package com.homepage.careerdoctor.domain;

import com.homepage.careerdoctor.util.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Embeddable
@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "DEGREES")
public class Degree extends BaseEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "degree_id")
    private Long degreeId;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "certificate_id")
    private Certificate certificate;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private User user;

    @Enumerated(EnumType.STRING)
    @Column(name = "school_div")
    private SchoolDiv schoolDiv;

    @Column(name = "school_name")
    private String schoolName;

    @Column(name = "entrance_year")
    private String entranceYear;

    @Enumerated(EnumType.STRING)
    @Column(name = "entrance_div")
    private EntranceDiv entranceDiv;

    @Column(name = "graduate_year")
    private String graduateYear;

    @Enumerated(EnumType.STRING)
    @Column(name = "graduate_div")
    private GraduateDiv graduateDiv;

    @Column(name = "major")
    private String major;

    @Column(name = "credit")
    private float credit;



}
