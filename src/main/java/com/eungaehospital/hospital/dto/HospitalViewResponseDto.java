package com.eungaehospital.hospital.dto;

import java.util.List;
import java.util.stream.Collectors;

import com.eungaehospital.hospital.domain.Hospital;
import com.eungaehospital.hospital.domain.HospitalImage;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class HospitalViewResponseDto {

	private String hospitalId;
	private String name;
	private String notice;
	private int deposit; //예약금
	private String contact; //연락처
	private String address;
	private List<HospitalImageResponseDto> hospitalImageResponseDtoList;

	public static HospitalViewResponseDto toDto(Hospital entity, List<HospitalImage> hospitalImageList) {

		List<HospitalImageResponseDto> hospitalImageDtoList = hospitalImageList.stream()
			.map(HospitalImageResponseDto::toDto)
			.collect(Collectors.toList());

		return HospitalViewResponseDto.builder()
			.hospitalId(entity.getHospitalId())
			.name(entity.getName())
			.notice(entity.getNotice())
			.deposit(entity.getDeposit())
			.contact(entity.getContact())
			.address(entity.getAddress())
			.hospitalImageResponseDtoList(hospitalImageDtoList)
			.build();
	}
}
