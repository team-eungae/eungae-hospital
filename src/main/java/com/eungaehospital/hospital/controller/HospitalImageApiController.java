package com.eungaehospital.hospital.controller;

import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.eungaehospital.hospital.service.HospitalImageService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RequestMapping("/hospital/images")
@RestController
public class HospitalImageApiController {
	private final HospitalImageService hospitalImageService;


	@PatchMapping("/form")
	public String deleteHospitalImage(@RequestParam(value = "hospitalImageSeq") Long hospitalImageSeq){
		return hospitalImageService.deleteHospitalImage(hospitalImageSeq);
	}

}
