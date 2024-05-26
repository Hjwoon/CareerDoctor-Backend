package com.homepage.careerdoctor.domain;

import com.homepage.careerdoctor.util.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "LANGUAGES")
public class Language extends BaseEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "language_id")
    private Long languageId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "spec_id")
    private SpecCertificate specCertificate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private User user;

    @Enumerated(EnumType.STRING)
    @Column(name = "language_name")
    private LanguageName languageName;

    @Enumerated(EnumType.STRING)
    @Column(name = "language_level")
    private LanguageLevel languageLevel;


}
