package com.eungaehospital.hospital.controller;

import com.eungaehospital.doctor.dto.DoctorResponseDto;
import com.eungaehospital.doctor.service.DoctorService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
@RequiredArgsConstructor
@RequestMapping("/hospital")
@Controller
public class HospitalViewController {

	private final DoctorService doctorService;

	@GetMapping("/main")
	public String getMainView(@AuthenticationPrincipal UserDetails userDetails,
							  Model model) {
		List<DoctorResponseDto> doctorList = doctorService.findDoctorsByHospitalSeq(userDetails.getUsername());
		model.addAttribute("doctorList", doctorList);
		return "hospital-main";
	}

	@GetMapping("/profile")
	public String hospitalPage() {
		return "contents/hospital-detail";
	}

	@GetMapping("/profile/form")
	public String updateHospital() {
		return "contents/hospital-update";
	}

	@GetMapping("/doctors/form")
	public String addDoctor() {
		return "contents/hospital-admin-doctor-form";
	}

	@GetMapping("/schedule")
	public String hospitalSchedule() {
		return "contents/hospital-schedule";
	}

	@GetMapping("/schedule/form")
	public String updateHospitalSchedule() {
		return "contents/hospital-schedule-update";
	}

}
