package com.eungaehospital.appointment.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.eungaehospital.appointment.domain.Appointment;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

	@Query("select a from Appointment a"
		+ " where a.hospital.hospitalSeq = :hospitalSeq"
		+ " and a.appointmentDate = :appointmentDate"
		+ " and a.status = 'APPOINTMENT'")
	List<Appointment> getAppointmentByAppointmentDateAndHospitalId(
		Long hospitalSeq,
		@Param("appointmentDate") LocalDate appointmentDate);
}
