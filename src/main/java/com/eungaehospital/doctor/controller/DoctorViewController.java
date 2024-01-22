package com.eungaehospital.doctor.controller;

import com.eungaehospital.doctor.service.DoctorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@RequiredArgsConstructor
@Controller
public class DoctorViewController {

    private final DoctorService doctorService;

    @GetMapping("/doctors")
    public String getMainView() {
        return "contents/doctor-list";
    }
}
