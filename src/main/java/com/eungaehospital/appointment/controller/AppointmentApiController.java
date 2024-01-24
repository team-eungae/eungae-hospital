package com.eungaehospital.appointment.controller;

import java.util.List;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eungaehospital.appointment.dto.AppointmentResponseDto;
import com.eungaehospital.appointment.service.AppointmentService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@RestController
public class AppointmentApiController {

	private final AppointmentService appointmentService;

	@GetMapping("/appointments")
	public List<AppointmentResponseDto> getAppointmentList(
		@AuthenticationPrincipal UserDetails userDetails,
		String selectDate
	) {
		return appointmentService.getAppointmentListBy(
			userDetails.getUsername(), selectDate);
	}

}