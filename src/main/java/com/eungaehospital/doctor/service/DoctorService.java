package com.eungaehospital.doctor.service;

import com.eungaehospital.doctor.domain.Doctor;
import com.eungaehospital.doctor.dto.DoctorRequestDto;
import com.eungaehospital.doctor.domain.DoctorStatus;
import com.eungaehospital.doctor.dto.DoctorResponseDto;
import com.eungaehospital.doctor.dto.DoctorUpdateRequestDto;
import com.eungaehospital.doctor.repository.DoctorRepository;
import com.eungaehospital.file.ResultFileStore;
import com.eungaehospital.hospital.domain.Hospital;
import com.eungaehospital.hospital.repository.HospitalRepository;

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

    @Transactional(readOnly = true)
    public DoctorResponseDto getDoctorById(Long doctorSeq) {
        Doctor doctor = doctorRepository.findById(doctorSeq)
                .orElseThrow(() -> new IllegalArgumentException("can not found doctor. doctorSeq = {%d}".formatted(doctorSeq)));
        return DoctorResponseDto.toDto(doctor);
    }

    @Transactional
    public void updateDoctor(DoctorUpdateRequestDto doctorUpdateRequestDto, ResultFileStore resultFileStore) {
        Doctor doctor = doctorRepository.findById(doctorUpdateRequestDto.getDoctorSeq())
                .orElseThrow(() -> new IllegalArgumentException("can not found doctor. doctorSeq = {%d}".formatted(doctorUpdateRequestDto.getDoctorSeq())));

        if (resultFileStore.getStoreFileName() == null) {
            doctor.updateDoctor(doctorUpdateRequestDto.getTreatmentPossible());
        } else {
            doctor.updateDoctor(doctorUpdateRequestDto.getTreatmentPossible(),
                    resultFileStore.getStoreFileName());
        }
        doctorRepository.save(doctor);
    }
}
