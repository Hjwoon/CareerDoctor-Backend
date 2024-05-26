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

    @Column(name = "NAME")
    private String name;

    @Column(name = "BIRTH")
    private String birth;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Enumerated(EnumType.STRING)
    private Level level;

    @Column(name = "IS_PUBLIC")
    private boolean isPublic;

    @OneToOne(mappedBy = "specCertificate", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Degree degree;

    @OneToMany(mappedBy = "specCertificate")
    private List<Certificate> certificates;

    @OneToMany(mappedBy = "specCertificate")
    private List<Activity> activities;

    @OneToMany(mappedBy = "specCertificate")
    private List<Language> languages;

    @OneToMany(mappedBy = "specCertificate")
    private List<Career> careers;

    @OneToMany(mappedBy = "specCertificate")
    private List<Etc> etcs;

    @ManyToOne
    @JoinColumn(name = "MEMBER_ID")
    private User user;

}
