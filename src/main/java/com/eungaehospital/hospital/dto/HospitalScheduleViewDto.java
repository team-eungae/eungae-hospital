package com.eungaehospital.hospital.dto;

import com.eungaehospital.hospital.domain.HospitalSchedule;

import jakarta.validation.constraints.Pattern;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class HospitalScheduleViewDto {
	@Pattern(
		regexp = "^(0[0-9]|1[0-9]|2[0-3])[0-5][0-9]$",
		message = "서식에 맞게 입력해주세요"
	)
	private String lunchHour;
	@Pattern(
		regexp = "^(0[0-9]|1[0-9]|2[0-3])[0-5][0-9]$",
		message = "서식에 맞게 입력해주세요"
	)
	private String lunchEndHour;
	@Pattern(
		regexp = "^(0[0-9]|1[0-9]|2[0-3])[0-5][0-9]$",
		message = "서식에 맞게 입력해주세요"
	)
	private String monOpen;
	@Pattern(
		regexp = "^(0[0-9]|1[0-9]|2[0-3])[0-5][0-9]$",
		message = "서식에 맞게 입력해주세요"
	)
	private String monClose;
	@Pattern(
		regexp = "^(0[0-9]|1[0-9]|2[0-3])[0-5][0-9]$",
		message = "서식에 맞게 입력해주세요"
	)
	private String tueOpen;
	@Pattern(
		regexp = "^(0[0-9]|1[0-9]|2[0-3])[0-5][0-9]$",
		message = "서식에 맞게 입력해주세요"
	)
	private String tueClose;
	@Pattern(
		regexp = "^(0[0-9]|1[0-9]|2[0-3])[0-5][0-9]$",
		message = "서식에 맞게 입력해주세요"
	)
	private String wedOpen;
	@Pattern(
		regexp = "^(0[0-9]|1[0-9]|2[0-3])[0-5][0-9]$",
		message = "서식에 맞게 입력해주세요"
	)
	private String wedClose;
	@Pattern(
		regexp = "^(0[0-9]|1[0-9]|2[0-3])[0-5][0-9]$",
		message = "서식에 맞게 입력해주세요"
	)
	private String thuOpen;
	@Pattern(
		regexp = "^(0[0-9]|1[0-9]|2[0-3])[0-5][0-9]$",
		message = "서식에 맞게 입력해주세요"
	)
	private String thuClose;
	@Pattern(
		regexp = "^(0[0-9]|1[0-9]|2[0-3])[0-5][0-9]$",
		message = "서식에 맞게 입력해주세요"
	)
	private String friOpen;
	@Pattern(
		regexp = "^(0[0-9]|1[0-9]|2[0-3])[0-5][0-9]$",
		message = "서식에 맞게 입력해주세요"
	)
	private String friClose;
	@Pattern(
		regexp = "^(0[0-9]|1[0-9]|2[0-3])[0-5][0-9]$",
		message = "서식에 맞게 입력해주세요"
	)
	private String satOpen;
	@Pattern(
		regexp = "^(0[0-9]|1[0-9]|2[0-3])[0-5][0-9]$",
		message = "서식에 맞게 입력해주세요"
	)
	private String satClose;
	@Pattern(
		regexp = "^(0[0-9]|1[0-9]|2[0-3])[0-5][0-9]$",
		message = "서식에 맞게 입력해주세요"
	)
	private String sunOpen;
	@Pattern(
		regexp = "^(0[0-9]|1[0-9]|2[0-3])[0-5][0-9]$",
		message = "서식에 맞게 입력해주세요"
	)
	private String sunClose;

	public static HospitalScheduleViewDto toDto(HospitalSchedule hospitalSchedule) {
		return HospitalScheduleViewDto.builder()
			.lunchHour(hospitalSchedule.getLunchHour())
			.lunchEndHour(hospitalSchedule.getLunchEndHour())
			.monOpen(hospitalSchedule.getMonOpen())
			.monClose(hospitalSchedule.getMonClose())
			.tueOpen(hospitalSchedule.getTueOpen())
			.tueClose(hospitalSchedule.getTueClose())
			.wedOpen(hospitalSchedule.getWedOpen())
			.wedClose(hospitalSchedule.getWedClose())
			.thuOpen(hospitalSchedule.getThuOpen())
			.thuClose(hospitalSchedule.getThuClose())
			.friOpen(hospitalSchedule.getFriOpen())
			.friClose(hospitalSchedule.getFriClose())
			.satOpen(hospitalSchedule.getSatOpen())
			.satClose(hospitalSchedule.getSatClose())
			.sunOpen(hospitalSchedule.getSunOpen())
			.sunClose(hospitalSchedule.getSunClose())
			.build();
	}
}