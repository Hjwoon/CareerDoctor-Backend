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
@Table(name = "SPEC_CERTIFICATES")
public class SpecCertificate extends BaseEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "spec_id")
    private Long specId;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private User user;

    @Embedded
    private Degree degree;

    @OneToMany(mappedBy = "specCertificate")
    private List<Language> languages;

    @OneToMany(mappedBy = "specCertificate")
    private List<Certificate> certificates;

    @OneToMany(mappedBy = "specCertificate")
    private List<Activity> activities;

    @OneToMany(mappedBy = "specCertificate")
    private List<Career> careers;

    @OneToMany(mappedBy = "specCertificate")
    private List<Etc> etcs;

    @Column(name = "name")
    private String name;

    @Column(name = "birth") // ex) 20241209
    private String birth;

    @Enumerated(EnumType.STRING)
    @Column(name = "gender")
    private Gender gender;

    @Enumerated(EnumType.STRING)
    @Column(name = "level")
    private Level level;

    @Column(name = "is_public")
    private boolean isPublic;


}
