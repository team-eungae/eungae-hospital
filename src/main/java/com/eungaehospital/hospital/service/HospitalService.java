package com.eungaehospital.hospital.service;

import com.eungaehospital.doctor.dto.DoctorResponseDto;
import com.eungaehospital.doctor.repository.DoctorRepository;
import com.eungaehospital.hospital.repository.HospitalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class HospitalService {

	private final HospitalRepository hospitalRepository;

}
