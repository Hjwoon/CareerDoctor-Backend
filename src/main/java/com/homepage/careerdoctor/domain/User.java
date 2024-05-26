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
@Table(name = "USERS")
public class User extends BaseEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long memberId;

    @Column(name = "user_id")
    private String userId;

    @Column(name = "password")
    private String password;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private SpecCertificate specCertificate;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<SpecReport> specReports;

//    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
//    private List<Post> posts;

//    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
//    private List<Like> likes;

//    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
//    private List<Scrap> scraps;
//
//    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
//    private List<Comment> comments;
//
//    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
//    private List<Vote> votes;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Activity> activities;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Career> careers;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Language> languages;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Certificate> certificates;

    @Embedded
    private Degree degree;

}
