package com.eungaehospital.doctor.service;

import com.eungaehospital.doctor.domain.Doctor;
import com.eungaehospital.doctor.domain.DoctorStatus;
import com.eungaehospital.doctor.dto.DoctorResponseDto;
import com.eungaehospital.doctor.repository.DoctorRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
public class DoctorService {

    private final DoctorRepository doctorRepository;

    @Transactional(readOnly = true)
    public List<DoctorResponseDto> findDoctorsByHospitalSeq(String hospitalId) {

        List<Doctor> doctorList = doctorRepository.findAllByHospitalHospitalId(hospitalId);

        return doctorList.stream()
                .map(DoctorResponseDto::toDto)
                .collect(Collectors.toList());
    }

    @Transactional
    public DoctorStatus changeDoctorStatus(Long doctorSeq) {
        Doctor doctor = doctorRepository.findById(doctorSeq)
                .orElseThrow(() -> new IllegalStateException("can not found doctor. doctorSeq = {%d})".formatted(doctorSeq)));
        if (doctor.getStatus() == DoctorStatus.ON) {
            doctor.setStatus(DoctorStatus.OFF);
            return DoctorStatus.OFF;
        }
        doctor.setStatus(DoctorStatus.ON);
        return DoctorStatus.ON;
    }
}
