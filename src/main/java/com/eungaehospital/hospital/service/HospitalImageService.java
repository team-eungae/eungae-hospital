package com.eungaehospital.hospital.service;

import java.util.NoSuchElementException;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.eungaehospital.hospital.domain.HospitalImage;
import com.eungaehospital.hospital.repository.HospitalImageRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class HospitalImageService {
	private final HospitalImageRepository hospitalImageRepository;

	@Transactional
	public String deleteHospitalImage(Long hospitalImageSeq) {
		HospitalImage hospitalImage = hospitalImageRepository.findById(hospitalImageSeq)
			.orElseThrow(() -> new NoSuchElementException("hospital Image not found {%d}".formatted(hospitalImageSeq)));

		hospitalImage.deleted();

		return "hospital Image deleted successfully";
	}
}
