package com.eungaehospital.doctor.repository;

import com.eungaehospital.doctor.domain.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Long> {
	List<Doctor> findAllByHospitalHospitalId(String hospitalId);

}
