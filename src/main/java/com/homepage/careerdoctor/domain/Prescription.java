package com.homepage.careerdoctor.domain;

import com.homepage.careerdoctor.util.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "PRESCRIPTION")
public class Prescription extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PRESCRIPTION_ID")
    private Long prescriptionId;

    @Column(name = "PRES_REASON")
    private String presReason;

    @Column(name = "PRES_CONTENT")
    private String presPeriod;

    @Column(name = "PERCENT")
    private int percent;
}
