package com.eungaehospital.doctor.controller;

import java.io.IOException;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import com.eungaehospital.doctor.dto.DoctorRequestDto;
import com.eungaehospital.doctor.service.DoctorService;
import com.eungaehospital.file.FileStore;
import com.eungaehospital.file.ResultFileStore;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class DoctorViewController {

	private final DoctorService doctorService;

	private final FileStore fileStore;

	@GetMapping("/doctor/form")
	public String getSaveDoctorForm(Model model) {
		model.addAttribute("doctorRequestDto", new DoctorRequestDto());
		return "contents/hospital-admin-doctor-form";
	}

	@PostMapping("/doctor")
	public String saveDoctor(
		@Valid DoctorRequestDto doctorRequestDto,
		BindingResult bindingResult,
		MultipartFile profileImage,
		@AuthenticationPrincipal UserDetails hospital
	) throws IOException {
		if (bindingResult.hasErrors()) {
			return "contents/hospital-admin-doctor-form";
		}

		ResultFileStore resultFileStore = fileStore.storeFile(profileImage);

		String hospitalId = hospital.getUsername();

		doctorService.saveDoctor(doctorRequestDto, resultFileStore, hospitalId);

		return "redirect:/main";
	}

}
