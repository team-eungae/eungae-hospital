package com.eungaehospital.doctor.dto;

import com.eungaehospital.doctor.domain.Doctor;
import com.eungaehospital.doctor.domain.DoctorStatus;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DoctorResponseDto {
    private Long doctorSeq;
    private String name;
    private DoctorStatus status;
    private int treatmentPossible;
    private String doctorProfileImage;
    private int currentAppointment;

    public static DoctorResponseDto toDto(Doctor doctor) {
        return DoctorResponseDto.builder()
                .doctorSeq(doctor.getDoctorSeq())
                .name(doctor.getName())
                .status(doctor.getStatus())
                .treatmentPossible(doctor.getTreatmentPossible())
                .doctorProfileImage(doctor.getDoctorProfileImage())
                .build();
    }

    public static DoctorResponseDto toDto(Doctor doctor, int currentAppointment) {
        return DoctorResponseDto.builder()
                .doctorSeq(doctor.getDoctorSeq())
                .name(doctor.getName())
                .status(doctor.getStatus())
                .treatmentPossible(doctor.getTreatmentPossible())
                .doctorProfileImage(doctor.getDoctorProfileImage())
                .currentAppointment(currentAppointment)
                .build();
    }
}
