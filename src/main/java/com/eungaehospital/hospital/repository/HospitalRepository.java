package com.eungaehospital.hospital.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.eungaehospital.hospital.domain.Hospital;

@Repository
public interface HospitalRepository extends JpaRepository<Hospital, Long> {
	Optional<Hospital> findByHospitalId(String hospitalId);

	@Query("select h from Hospital h"
		+ " join fetch h.hospitalSchedule")
	Optional<Hospital> findWithSchedule(String hospitalId);
}
