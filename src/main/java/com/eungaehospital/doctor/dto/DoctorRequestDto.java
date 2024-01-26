package com.eungaehospital.doctor.dto;

import org.hibernate.validator.constraints.Length;

import com.eungaehospital.doctor.domain.Doctor;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class DoctorRequestDto {

	@NotBlank
	@Length(min = 2, max = 10)
	private String name;

	@NotNull
	private Integer treatmentPossible;

	public static Doctor toEntity(DoctorRequestDto dto, String profileImage) {
		return Doctor.builder()
			.name(dto.getName())
			.treatmentPossible(dto.getTreatmentPossible())
			.doctorProfileImage(profileImage)
			.build();
	}

}
