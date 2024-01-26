package com.eungaehospital.hospital.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.eungaehospital.hospital.domain.Hospital;
import com.eungaehospital.hospital.domain.HospitalImage;
import com.eungaehospital.hospital.dto.HospitalScheduleViewDto;
import com.eungaehospital.hospital.dto.HospitalViewResponseDto;
import com.eungaehospital.hospital.repository.HospitalImageRepository;
import com.eungaehospital.hospital.repository.HospitalRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class HospitalService {

	private final HospitalRepository hospitalRepository;
	private final HospitalImageRepository hospitalImageRepository;

	@Transactional(readOnly = true)
	public HospitalViewResponseDto getHospitalByHospitalId(String hospitalId) {
		Hospital hospital = hospitalRepository.findByHospitalId(hospitalId).get();
		List<HospitalImage> hospitalImageList = hospitalImageRepository.findAllByHospital(hospital);

		return HospitalViewResponseDto.toDto(hospital, hospitalImageList);
	}

	@Transactional(readOnly = true)
	public HospitalScheduleViewDto getHospitalSchedule(String hospitalId) {
		Hospital hospital = hospitalRepository.findWithSchedule(hospitalId)
			.orElseThrow(() -> new IllegalStateException("Can not found Hospital"));
		return HospitalScheduleViewDto.toDto(hospital.getHospitalSchedule());
	}

	@Transactional
	public void updateHospitalSchedule(String hospitalId, HospitalScheduleViewDto hospitalScheduleViewDto) {
		Hospital hospital = hospitalRepository.findWithSchedule(hospitalId)
			.orElseThrow(() -> new IllegalStateException("Can not found Hospital"));
		hospital.getHospitalSchedule().update(hospitalScheduleViewDto);
	}
}
