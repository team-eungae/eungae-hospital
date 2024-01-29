package com.eungaehospital.hospital.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.eungaehospital.hospital.domain.Hospital;

@Repository
public interface HospitalRepository extends JpaRepository<Hospital, Long> {

	@Query("select h from Hospital h"
		+ " where h.hospitalId = :hospitalId"
		+ " and h.deleteYN = 'N'")
	Optional<Hospital> findByHospitalId(@Param("hospitalId") String hospitalId);

}
