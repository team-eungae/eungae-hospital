package com.eungaehospital.appointment.dto;

import com.eungaehospital.appointment.domain.Appointment;
import com.eungaehospital.appointment.domain.AppointmentStatus;
import com.eungaehospital.appointment.domain.AppointmentSort;
import com.eungaehospital.doctor.domain.Doctor;
import com.eungaehospital.hospital.domain.Hospital;
import com.eungaehospital.member.domain.Children;
import com.eungaehospital.member.domain.Member;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Data
public class AppointmentRequestDto {

    private String appointmentHHMM;

    @NotNull(message = "의사 선생님을 선택해주세요.")
    private long doctorSeq;

    @NotBlank(message = "이름 입력은 필수입니다.")
    private String name;

    @Pattern(
            regexp = "^(19|20)\\d\\d(0[1-9]|1[0-2])(0[1-9]|[12][0-9]|3[01])$",
            message = "서식에 맞게 입력해주세요"
    )
    private String birth;

    @NotBlank(message = "성별 입력은 필수입니다.")
    private String gender;

    private AppointmentSort sort;

    private String note;

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HHmm");

    public static Appointment toEntity(AppointmentRequestDto appointmentRequestDto,
                                       Doctor doctor, Children children, Member member, Hospital hospital) {
        return Appointment.builder()
                // 예약 상태 추가
                .status(AppointmentStatus.APPOINTMENT)
                .children(children)
                .doctor(doctor)
                .member(member)
                .hospital(hospital)
                .appointmentDate(LocalDate.now())
                .appointmentHHMM(LocalDateTime.now().format(appointmentRequestDto.formatter))
                .note(appointmentRequestDto.getNote())
                .sort(AppointmentSort.ONSITE)
                .build();
    }

}
