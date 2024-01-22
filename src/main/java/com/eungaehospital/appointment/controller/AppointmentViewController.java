package com.eungaehospital.appointment.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.eungaehospital.appointment.dto.AppointmentResponseDto;
import com.eungaehospital.appointment.service.AppointmentService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class AppointmentViewController {
	// private final AppointmentService appointmentService;
	//
	// @GetMapping("/main")
	// public String appointmentList(Model model, @PathVariable String date, @RequestParam String hospitalId) {
	// 	List<AppointmentResponseDto> appointments = appointmentService.getAppointmentListBy(hospitalId, date);
	// 	model.addAttribute("appointments", appointments);
	// 	return "hospital-main";
	// }
}
