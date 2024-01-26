package com.eungaehospital.appointment.dto;

import com.eungaehospital.appointment.domain.AppointmentStatus;

import lombok.Data;

@Data
public class AppointmentVisitedRequestDto {
		private Long appointmentSeq;
		private AppointmentStatus status;

}
