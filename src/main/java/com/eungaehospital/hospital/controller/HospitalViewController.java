package com.eungaehospital.hospital.controller;

import com.eungaehospital.doctor.dto.DoctorResponseDto;
import com.eungaehospital.doctor.service.DoctorService;
import com.eungaehospital.hospital.service.HospitalService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
@RequiredArgsConstructor
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

	@GetMapping("/hospital/form")
	public String updateHospital() {
		return "contents/hospital-update";
	}

	@GetMapping("/hospital/schedule")
	public String hospitalSchedule() {
		return "contents/hospital-schedule";
	}
}
