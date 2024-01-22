package com.eungaehospital.doctor.service;

import com.eungaehospital.doctor.domain.Doctor;
import com.eungaehospital.doctor.dto.DoctorRequestDto;
import com.eungaehospital.doctor.domain.DoctorStatus;
import com.eungaehospital.doctor.dto.DoctorResponseDto;
import com.eungaehospital.doctor.repository.DoctorRepository;
import com.eungaehospital.file.ResultFileStore;
import com.eungaehospital.hospital.domain.Hospital;
import com.eungaehospital.hospital.repository.HospitalRepository;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
public class DoctorService {

    private final DoctorRepository doctorRepository;
    private final HospitalRepository hospitalRepository;

    @Transactional(readOnly = true)
    public List<DoctorResponseDto> findDoctorsByHospitalSeq(String hospitalId) {

        List<Doctor> doctorList = doctorRepository.findAllByHospitalHospitalId(hospitalId);

        return doctorList.stream()
            .map(DoctorResponseDto::toDto)
            .collect(Collectors.toList());
    }

    @Transactional
    public void saveDoctor(
        DoctorRequestDto doctorRequestDto,
        ResultFileStore resultFileStore,
        String id) {

        Doctor doctor = DoctorRequestDto.toEntity(doctorRequestDto, resultFileStore.getStoreFileName());

        Hospital hospital = hospitalRepository.findByHospitalId(id).get();
        doctor.setHospital(hospital);

        doctorRepository.save(doctor);
    }

    @Transactional
    public boolean changeDoctorStatus(Long doctorSeq) {
        Doctor doctor = doctorRepository.findById(doctorSeq)
                .orElseThrow(() -> new IllegalStateException("can not found doctor. doctorSeq = {%d})".formatted(doctorSeq)));
        if (doctor.getStatus() == DoctorStatus.ON) {
            doctor.setStatus(DoctorStatus.OFF);
            return false;
        }
        doctor.setStatus(DoctorStatus.ON);
        return true;
    }
}
