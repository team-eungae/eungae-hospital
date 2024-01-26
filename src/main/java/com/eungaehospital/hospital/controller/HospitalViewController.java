package com.eungaehospital.hospital.controller;

import java.util.List;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.eungaehospital.doctor.dto.DoctorResponseDto;
import com.eungaehospital.doctor.service.DoctorService;
import com.eungaehospital.hospital.dto.HospitalViewResponseDto;
import com.eungaehospital.hospital.service.HospitalService;

import lombok.RequiredArgsConstructor;
@RequiredArgsConstructor
@RequestMapping("/hospital")
@Controller
public class HospitalViewController {
	private final HospitalService hospitalService;
	private final DoctorService doctorService;

	@GetMapping("/main")
	public String getMainView(@AuthenticationPrincipal UserDetails userDetails,
							  Model model) {
		List<DoctorResponseDto> doctorList = doctorService.findDoctorsByHospitalId(userDetails.getUsername());
		model.addAttribute("doctorList", doctorList);
		return "hospital-main";
	}

	@GetMapping("/profile")
	public String hospitalPage(@AuthenticationPrincipal UserDetails userDetails, Model model) {
		HospitalViewResponseDto hospital = hospitalService.getHospitalByHospitalId(
			userDetails.getUsername());
		model.addAttribute("hospital", hospital);
		return "contents/hospital-detail";
	}

	@GetMapping("/profile/form")
	public String updateHospital(@AuthenticationPrincipal UserDetails userDetails, Model model) {
		HospitalViewResponseDto hospital = hospitalService.getHospitalByHospitalId(
			userDetails.getUsername());
		model.addAttribute("hospital", hospital);
		return "contents/hospital-update";
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
