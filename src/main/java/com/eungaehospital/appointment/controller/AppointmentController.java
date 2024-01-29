package com.eungaehospital.appointment.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.eungaehospital.appointment.service.AppointmentService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RequestMapping("/hospital")
@Controller
public class AppointmentController {
	private final AppointmentService appointmentService;

	@GetMapping("/appointments/{appointmentSeq}/status/{status}")
	public String VisitedAppointment(@PathVariable Long appointmentSeq, @PathVariable String status) {
		appointmentService.changeAppointmentStatus(appointmentSeq, status);

		return "redirect:/hospital/main";
	}

}
