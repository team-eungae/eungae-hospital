package com.eungaehospital.hospital.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.eungaehospital.doctor.dto.DoctorResponseDto;
import com.eungaehospital.doctor.service.DoctorService;

import com.eungaehospital.hospital.dto.HospitalScheduleViewDto;
import com.eungaehospital.file.FileStore;
import com.eungaehospital.file.ResultFileStore;
import com.eungaehospital.hospital.dto.HospitalImageResponseDto;
import com.eungaehospital.hospital.dto.HospitalUpdateRequestDto;
import com.eungaehospital.hospital.dto.HospitalViewResponseDto;
import com.eungaehospital.hospital.service.HospitalService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RequestMapping("/hospital")
@Controller
public class HospitalViewController {
	private final HospitalService hospitalService;
	private final DoctorService doctorService;
	private final FileStore fileStore;

	@GetMapping("/main")
	public String getMainView(@AuthenticationPrincipal UserDetails userDetails,
		Model model) {
		List<DoctorResponseDto> doctorList = doctorService.findDoctorsByHospitalId(userDetails.getUsername());
		model.addAttribute("doctorList", doctorList);
		return "hospital-main";
	}

	@GetMapping("/profile")
	public String hospitalPage(@AuthenticationPrincipal UserDetails userDetails, Model model) {
		HospitalViewResponseDto hospital = hospitalService.getHospitalByHospitalId(userDetails.getUsername());
		model.addAttribute("hospital", hospital);
		return "contents/hospital-detail";
	}

	@GetMapping("/profile/form")
	public String updateHospitalForm(@AuthenticationPrincipal UserDetails userDetails, Model model) {
		HospitalViewResponseDto hospital = hospitalService.getHospitalByHospitalId(userDetails.getUsername());

		model.addAttribute("hospital", hospital);
		return "contents/hospital-update";
	}

	@PostMapping("/profile/form")
	public String updateHospital(@AuthenticationPrincipal UserDetails userDetails,
		HospitalUpdateRequestDto hospitalUpdateRequestDto) {

		hospitalService.updateHospital(hospitalUpdateRequestDto, userDetails.getUsername());
		return "redirect:/hospital/profile";
	}

	@GetMapping("/images/form")
	public String updateHospitalImageForm(@AuthenticationPrincipal UserDetails userDetails, Model model) {
		List<HospitalImageResponseDto> hospitalImageResponseDtoList = hospitalService.getHospitalImagesByHospital(
			userDetails.getUsername());
		model.addAttribute("hospitalImageResponseDtoList", hospitalImageResponseDtoList);
		return "contents/hospital-images-update";
	}

	@PostMapping("/images/form")
	public String updateHospitalImage(@AuthenticationPrincipal UserDetails userDetails,
		@RequestParam(value = "newHospitalImageList") List<MultipartFile> newHospitalImageList) {
		List<ResultFileStore> resultFileStores = new ArrayList<>();

		if (newHospitalImageList.get(0).getOriginalFilename() != "") {
			resultFileStores = fileStore.storeFiles(newHospitalImageList);
			hospitalService.updateHospitalImageList(resultFileStores, userDetails.getUsername());
		}

		return "redirect:/hospital/profile";
	}

	@GetMapping("/doctors/form")
	public String addDoctor() {
		return "contents/hospital-admin-doctor-form";
	}

	@GetMapping("/schedule")
	public String hospitalSchedule(@AuthenticationPrincipal UserDetails userDetails, Model model) {
		HospitalScheduleViewDto hospitalScheduleDto = hospitalService.getHospitalSchedule(
			userDetails.getUsername());
		model.addAttribute("hospitalScheduleDto", hospitalScheduleDto);
		return "contents/hospital-schedule";
	}

	@GetMapping("/schedule/form")
	public String updateHospitalScheduleForm(@AuthenticationPrincipal UserDetails userDetails, Model model) {
		HospitalScheduleViewDto hospitalScheduleDto = hospitalService.getHospitalSchedule(
			userDetails.getUsername());
		model.addAttribute("hospitalScheduleViewDto", hospitalScheduleDto);
		model.addAttribute("hospitalResponseScheduleDto", HospitalScheduleViewDto.builder().build());
		return "contents/hospital-schedule-update";
	}

	@PostMapping("/schedule/form")
	public String updateHospitalSchedule(
		@AuthenticationPrincipal UserDetails userDetails,
		@Valid HospitalScheduleViewDto hospitalScheduleViewDto,
		BindingResult bindingResult,
		Model model
	) {
		if (bindingResult.hasErrors()) {
			model.addAttribute("hospitalScheduleViewDto", hospitalScheduleViewDto);
			return "contents/hospital-schedule-update";
		}
		hospitalService.updateHospitalSchedule(userDetails.getUsername(), hospitalScheduleViewDto);
		return "redirect:/hospital/schedule";
	}
}
