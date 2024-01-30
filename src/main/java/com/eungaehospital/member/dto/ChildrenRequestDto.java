package com.eungaehospital.member.dto;

import com.eungaehospital.member.domain.Children;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ChildrenRequestDto {

	@NotBlank(message = "이름 입력은 필수입니다.")
	private String name;

	@Pattern(
		regexp = "^(19|20)\\d\\d(0[1-9]|1[0-2])(0[1-9]|[12][0-9]|3[01])$",
		message = "서식에 맞게 입력해주세요"
	)
	private String birthDate;

	@NotBlank(message = "성별 입력은 필수입니다.")
	private String gender;

	public static Children toEntity(ChildrenRequestDto childrenRequestDto) {
		return Children.builder()
			.name(childrenRequestDto.getName())
			.birthDate(childrenRequestDto.getBirthDate())
			.gender(childrenRequestDto.getGender())
			.build();
	}
}