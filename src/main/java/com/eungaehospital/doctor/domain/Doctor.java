package com.eungaehospital.doctor.domain;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;

import com.eungaehospital.base.BaseEntity;
import com.eungaehospital.hospital.domain.Hospital;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@DynamicInsert
@Table(name = "doctor")
@Entity
public class Doctor extends BaseEntity {

    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Id
    private Long doctorSeq;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hospital_seq")
    private Hospital hospital;

    @Column(nullable = false)
    private String name;

    @Setter
    @Builder.Default
    @Enumerated(EnumType.STRING)
    private DoctorStatus status = DoctorStatus.OFF;

    @Column(nullable = false)
    private int treatmentPossible;

    private String doctorProfileImage;

    public Doctor(String name) {
        this.name = name;
    }

    //연관관계 편의 메소드
    public void setHospital(Hospital hospital) {
        this.hospital = hospital;
        hospital.addDoctor(this);
    }

    public void updateDoctor(int treatmentPossible, String doctorProfileImage) {
        this.treatmentPossible = treatmentPossible;
        this.doctorProfileImage = doctorProfileImage;
    }

    public void updateDoctor(int treatmentPossible) {
        this.treatmentPossible = treatmentPossible;
    }
}
