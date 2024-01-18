package com.eungaehospital.hospital.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HospitalViewController {

	@GetMapping("/main")
	public String getMainView() {
		return "hospital-main";
	}
}
