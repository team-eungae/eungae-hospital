package com.eungaehospital.appointment.controller;

import com.eungaehospital.appointment.dto.AppointmentRequestDto;
import com.eungaehospital.appointment.service.AppointmentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/hospital")
@Controller
public class AppointmentViewController {

    private final AppointmentService appointmentService;

    @GetMapping("/appointments/{appointmentSeq}/status/{status}")
    public String VisitedAppointment(@PathVariable Long appointmentSeq, @PathVariable String status) {
        appointmentService.changeAppointmentStatus(appointmentSeq, status);

        return "redirect:/hospital/main";
    }

    @PostMapping("/appointments")
    public String saveAppointment(
            @Valid AppointmentRequestDto appointmentRequestDto,
            @AuthenticationPrincipal UserDetails userDetails
    ) {
        String hospitalId = userDetails.getUsername();
        appointmentService.saveAppointment(appointmentRequestDto, hospitalId);
        return "redirect:/hospital/main";
    }
}
