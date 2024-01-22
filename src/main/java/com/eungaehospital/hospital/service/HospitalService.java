package com.eungaehospital.hospital.service;


import com.eungaehospital.hospital.repository.HospitalRepository;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@RequiredArgsConstructor
@Service
public class HospitalService {

	private final HospitalRepository hospitalRepository;



}
