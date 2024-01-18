package com.eungaehospital;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class HomeController {

	@GetMapping
	public String getLoginForm() {
		return "index";
	}

}
