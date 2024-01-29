package com.eungaehospital.doctor.controller;

import java.io.IOException;
import java.util.List;

import com.eungaehospital.appointment.service.AppointmentService;
import com.eungaehospital.doctor.dto.DoctorResponseDto;
import com.eungaehospital.doctor.dto.DoctorUpdateRequestDto;

import lombok.extern.slf4j.Slf4j;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;

import com.eungaehospital.doctor.dto.DoctorRequestDto;
import com.eungaehospital.doctor.service.DoctorService;
import com.eungaehospital.file.FileStore;
import com.eungaehospital.file.ResultFileStore;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/hospital")
@Controller
public class DoctorViewController {

	private final DoctorService doctorService;
	private final AppointmentService appointmentService;
	private final FileStore fileStore;

	@GetMapping("/doctor/form")
	public String getDoctorRegisterForm(Model model) {
		model.addAttribute("doctorRequestDto", new DoctorRequestDto());
		return "contents/hospital-admin-doctor-form";
	}

	@PostMapping("/doctor")
	public String saveDoctor(
		@Valid DoctorRequestDto doctorRequestDto,
		BindingResult bindingResult,
		MultipartFile profileImage,
		@AuthenticationPrincipal UserDetails userDetails
	) throws IOException {
		if (bindingResult.hasErrors()) {
			return "contents/hospital-admin-doctor-form";
		}

		ResultFileStore resultFileStore = fileStore.storeFile(profileImage);

		String hospitalId = userDetails.getUsername();

		doctorService.saveDoctor(doctorRequestDto, resultFileStore, hospitalId);

		return "redirect:/hospital/main";
	}

	@GetMapping("/doctors")
	public String getDoctorList(@AuthenticationPrincipal UserDetails userDetails,
		Model model) {
		List<DoctorResponseDto> doctorList = doctorService.findDoctorsByHospitalId(userDetails.getUsername());
		model.addAttribute("doctorList", doctorList);
		return "contents/doctor-list";
	}

	@GetMapping("/doctors/{doctorSeq}/form")
	public String updateProfile(@PathVariable Long doctorSeq, Model model) {
		DoctorResponseDto doctorResponseDto = doctorService.getDoctorById(doctorSeq);
		model.addAttribute("doctor", doctorResponseDto);
		return "contents/doctor-update";
	}

	@PostMapping("/doctors")
	public String updateDoctor(
		@Valid DoctorUpdateRequestDto doctorUpdateRequestDto,
		BindingResult bindingResult,
		@RequestParam("profileImage") MultipartFile profileImage
	) {
		if (bindingResult.hasErrors()) {
			return "contents/doctor-update";
		}
		ResultFileStore resultFileStore = fileStore.storeProfileFile(profileImage);
		doctorService.updateDoctor(doctorUpdateRequestDto, resultFileStore);
		return "redirect:/hospital/doctors";
	}

	@GetMapping("/doctors/{doctorSeq}")
	public String deleteDoctor(@PathVariable Long doctorSeq) {
		try {
			doctorService.deleteDoctor(doctorSeq);
			return "redirect:/hospital/doctors";
		} catch (IllegalStateException e) {
			return "redirect:/hospital/doctors";
		}
	}
}
