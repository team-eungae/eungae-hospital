package com.eungaehospital.hospital.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class HospitalUpdateRequestDto {
	private Long hospitalSeq;
	private String hospitalId;
	private int deposit;
	private String name;
	private String notice;
	private String contact; //연락처
	private String address;

}
