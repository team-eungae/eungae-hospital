package com.eungaehospital.hospital.dto;


import com.eungaehospital.hospital.domain.HospitalImage;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class HospitalImageResponseDto {
	private Long hospitalImageSeq;
	private String storeFileName;
	private String originFileName;

	public static HospitalImageResponseDto toDto(HospitalImage hospitalImage){
		return HospitalImageResponseDto.builder()
			.hospitalImageSeq(hospitalImage.getHospitalImageSeq())
			.originFileName(hospitalImage.getOriginFileName())
			.storeFileName(hospitalImage.getStoreFileName())
			.build();
	}


}
