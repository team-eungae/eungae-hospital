package com.eungaehospital.appointment.dto;

import com.eungaehospital.appointment.domain.Appointment;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class AppointmentResponseDto {

	private Long appointmentSeq;
	private String childrenName;
	private String age;
	private String gender;
	private String appointmentHourMinute;
	private String doctor;
	private String note;
	private String status;

	public static AppointmentResponseDto toDto(Appointment appointment) {
		return AppointmentResponseDto.builder()
			.appointmentSeq(appointment.getAppointmentSeq())
			.childrenName(appointment.getChildren().getName())
			.age(appointment.getChildren().getBirthDate())
			.gender(appointment.getChildren().getGender())
			.appointmentHourMinute(appointment.getAppointmentHHMM())
			.doctor(appointment.getDoctor().getName())
			.note(appointment.getNote())
			.status(String.valueOf(appointment.getStatus()))
			.build();
	}
}
