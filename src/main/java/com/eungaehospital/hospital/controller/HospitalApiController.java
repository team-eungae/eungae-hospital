package com.eungaehospital.hospital.controller;

import com.eungaehospital.doctor.domain.Doctor;
import com.eungaehospital.doctor.dto.DoctorResponseDto;
import com.eungaehospital.hospital.service.HospitalService;
import lombok.RequiredArgsConstructor;


import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/hospital")
public class HospitalApiController {

    private final HospitalService hospitalService;



}
