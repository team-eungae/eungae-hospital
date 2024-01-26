package com.eungaehospital.doctor.repository;

import com.eungaehospital.doctor.domain.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Long> {

    @Query("select d from Doctor d"
            + " join fetch d.hospital"
            + " where d.hospital.hospitalId = :hospitalId"
            + " and d.deleteYN != 'Y'")
    List<Doctor> findAllByHospitalHospitalId(@Param("hospitalId") String hospitalId);

    @Query("select d from Doctor d"
            + " where d.doctorSeq = :doctorSeq"
            + " and d.deleteYN != 'Y'")
    Optional<Doctor> findByDoctorSeq(@Param("doctorSeq") Long doctorSeq);
}
