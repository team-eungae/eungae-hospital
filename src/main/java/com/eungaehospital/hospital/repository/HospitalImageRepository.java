package com.eungaehospital.hospital.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eungaehospital.hospital.domain.Hospital;
import com.eungaehospital.hospital.domain.HospitalImage;

public interface HospitalImageRepository extends JpaRepository<HospitalImage, Long> {
	List<HospitalImage> findAllByHospital(Hospital hospital);
 }
