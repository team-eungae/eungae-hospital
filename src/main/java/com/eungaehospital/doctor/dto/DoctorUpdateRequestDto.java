package com.eungaehospital.doctor.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class DoctorUpdateRequestDto {
    private Long doctorSeq;

    @NotNull
    private Integer treatmentPossible;
}
