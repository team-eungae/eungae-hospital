package com.eungaehospital.appointment.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.eungaehospital.appointment.domain.Appointment;
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
	public List<AppointmentResponseDto> getAppointmentListBy(String hospitalId, String selectDate) {
		Long hospitalSeq = hospitalRepository.findByHospitalId(hospitalId).get().getHospitalSeq();
		List<Appointment> appointment = appointmentRepository
			.getAppointmentByAppointmentDateAndHospitalId(
				hospitalSeq,
				convertStringToLocalDate(selectDate));

		return appointment.stream()
			.map(AppointmentResponseDto::toDto)
			.toList();
	}

	private LocalDate convertStringToLocalDate(String date) {
		return LocalDate.parse(date, DateTimeFormatter.ISO_DATE);
	}

}
