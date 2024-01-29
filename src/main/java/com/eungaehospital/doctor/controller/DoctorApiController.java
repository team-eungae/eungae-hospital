package com.eungaehospital.doctor.controller;

import com.eungaehospital.doctor.domain.DoctorStatus;
import com.eungaehospital.doctor.service.DoctorService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/hospital")
@RestController
public class DoctorApiController {
    private final DoctorService doctorService;

    @PatchMapping("/doctors/status")
    public DoctorStatus changeDoctorStatus(@RequestParam("doctorSeq") Long doctorSeq) {
        return doctorService.changeDoctorStatus(doctorSeq);
    }
}
