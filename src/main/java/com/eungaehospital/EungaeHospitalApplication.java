package com.eungaehospital;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class EungaeHospitalApplication {

	public static void main(String[] args) {
		SpringApplication.run(EungaeHospitalApplication.class, args);
	}

}
