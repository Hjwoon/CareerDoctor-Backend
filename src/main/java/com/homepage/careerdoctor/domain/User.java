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
@Table(name = "USER")
public class User extends BaseEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MEMBER_ID")
    private Long memberId;

    @Column(name = "USER_ID")
    private String userId;

    @Column(name = "PASSWORD")
    private String password;

    @Enumerated(EnumType.STRING)
    private Level level;


    @OneToMany(mappedBy = "user")
    private List<Liked> likeds;

    @OneToMany(mappedBy = "user")
    private List<Comment> comments;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<SpecCertificate> certificates;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<SpecReport> specReports;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Post> posts;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Liked> likes;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Scrap> scraps;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Vote> votes;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Activity> activities;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Career> careers;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Language> languages;


    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Etc> etcs;

    @OneToMany(mappedBy = "user")
    private List<Need> needs;

    @OneToMany(mappedBy = "user")
    private List<PresType> presTypes;

}
