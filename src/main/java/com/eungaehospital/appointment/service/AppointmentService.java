package com.eungaehospital.appointment.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.eungaehospital.appointment.domain.Appointment;
import com.eungaehospital.appointment.domain.AppointmentStatus;
import com.eungaehospital.appointment.dto.AppointmentResponseDto;
import com.eungaehospital.appointment.repository.AppointmentRepository;
import com.eungaehospital.hospital.repository.HospitalRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class AppointmentService {

	private final AppointmentRepository appointmentRepository;
	private final HospitalRepository hospitalRepository;

	@Transactional(readOnly = true)
	public List<AppointmentResponseDto> getAppointmentListBySelectedDate(String hospitalId, String selectDate) {
		Long hospitalSeq = hospitalRepository.findByHospitalId(hospitalId).get().getHospitalSeq();

		LocalDate appointmentDate = convertStringToLocalDate(selectDate);
		List<Appointment> appointment = appointmentRepository
			.getAppointmentByAppointmentDateAndHospitalId(
				hospitalSeq,
				appointmentDate);

		appointment.addAll(
			appointmentRepository.getDiagnosisAppointmentByAppointmentDateAndHospitalId(hospitalSeq, appointmentDate));

		return appointment.stream()
			.map(AppointmentResponseDto::toDto)
			.toList();
	}

	@Transactional
	public void changeAppointmentStatus(Long appointmentSeq, String status) {

		Appointment appointment = appointmentRepository.findById(appointmentSeq)
			.orElseThrow(() -> new IllegalStateException(
				"Cannot find Appointment. appointmentSeq = {%d}".formatted(appointmentSeq)));

		if (status.equals("visit")) {
			appointment.setStatus(AppointmentStatus.DIAGNOSIS);
		} else if (status.equals("restore")) {
			appointment.setStatus(AppointmentStatus.APPOINTMENT);
		}
	}

	private LocalDate convertStringToLocalDate(String date) {
		return LocalDate.parse(date, DateTimeFormatter.ISO_DATE);
	}

}