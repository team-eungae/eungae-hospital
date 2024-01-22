package com.eungaehospital.doctor.service;

import com.eungaehospital.doctor.domain.Doctor;
import com.eungaehospital.doctor.dto.DoctorResponseDto;
import com.eungaehospital.doctor.repository.DoctorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

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

}
