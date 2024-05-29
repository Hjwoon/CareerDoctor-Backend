package com.homepage.careerdoctor.domain;

import com.homepage.careerdoctor.util.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "SPEC_CERTIFICATE")
public class SpecCertificate extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SPEC_ID")
    private Long specId;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MEMBER_ID")
    private User user;

    @Column(name = "IS_PUBLIC")
    private boolean isPublic;

    @Column(name = "NAME")
    private String name;

    @Column(name = "BIRTH")
    private String birth;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Enumerated(EnumType.STRING)
    private SchoolDiv schoolDiv;

    @Column(name = "ENTRANCE_YEAR")
    private String entranceYear;

    @Enumerated(EnumType.STRING)
    private EntranceDiv entranceDiv;

    @Enumerated(EnumType.STRING)
    private GraduateDiv graduateDiv;

    @Column(name = "MAJOR")
    private String major;

    @Column(name = "AVG_CREDIT")
    private Double avgCredit;

    @ElementCollection
    @CollectionTable(name = "CERTIFICATE", joinColumns = @JoinColumn(name = "SPEC_ID"))
    @Column(name = "CERTIFICATE_NAME")
    private List<String> certificates;

    @ElementCollection
    @CollectionTable(name = "ACTIVITIES", joinColumns = @JoinColumn(name = "SPEC_ID"))
    @Column(name = "ACTIVITY_NAME")
    private List<String> activities;

    @ElementCollection
    @CollectionTable(name = "LANGUAGES", joinColumns = @JoinColumn(name = "SPEC_ID"))
    @Column(name = "LANGUAGE_NAME")
    private List<String> languages;

    @ElementCollection
    @CollectionTable(name = "CAREERS", joinColumns = @JoinColumn(name = "SPEC_ID"))
    @Column(name = "CAREER_NAME")
    private List<String> careers;

    @ElementCollection
    @CollectionTable(name = "ETCS", joinColumns = @JoinColumn(name = "SPEC_ID"))
    @Column(name = "ETC_NAME")
    private List<String> etcs;

}
