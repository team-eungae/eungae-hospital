package com.eungaehospital.appointment.service;

import com.eungaehospital.appointment.domain.Appointment;
import com.eungaehospital.appointment.domain.AppointmentStatus;
import com.eungaehospital.appointment.dto.AppointmentRequestDto;
import com.eungaehospital.appointment.dto.AppointmentResponseDto;
import com.eungaehospital.appointment.repository.AppointmentRepository;
import com.eungaehospital.doctor.domain.Doctor;
import com.eungaehospital.doctor.repository.DoctorRepository;
import com.eungaehospital.hospital.domain.Hospital;
import com.eungaehospital.hospital.repository.HospitalRepository;
import com.eungaehospital.member.domain.Children;
import com.eungaehospital.member.domain.Member;
import com.eungaehospital.member.repository.ChildrenRepository;
import com.eungaehospital.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.NoSuchElementException;

@Slf4j
@RequiredArgsConstructor
@Service
public class AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final HospitalRepository hospitalRepository;
    private final DoctorRepository doctorRepository;
    private final MemberRepository memberRepository;
    private final ChildrenRepository childrenRepository;


    @Transactional(readOnly = true)
    public List<AppointmentResponseDto> getAppointmentListBySelectedDate(String hospitalId, String selectDate) {
        Long hospitalSeq = hospitalRepository.findByHospitalId(hospitalId).get().getHospitalSeq();

        LocalDate appointmentDate = convertStringToLocalDate(selectDate);
        List<Appointment> appointment = appointmentRepository
                .getAppointmentByAppointmentDateAndHospitalId(
                        hospitalSeq,
                        appointmentDate);

        appointment.addAll(
                appointmentRepository.getDiagnosisAppointmentByAppointmentDateAndHospitalId(hospitalSeq, appointmentDate));

        return appointment.stream()
                .map(AppointmentResponseDto::toDto)
                .toList();
    }

    @Transactional
    public void changeAppointmentStatus(Long appointmentSeq, String status) {

        Appointment appointment = appointmentRepository.findById(appointmentSeq)
                .orElseThrow(() -> new IllegalStateException(
                        "Cannot find Appointment. appointmentSeq = {%d}".formatted(appointmentSeq)));

        if (status.equals("visit")) {
            appointment.setStatus(AppointmentStatus.DIAGNOSIS);
        } else if (status.equals("restore")) {
            appointment.setStatus(AppointmentStatus.APPOINTMENT);
        }
    }

    private LocalDate convertStringToLocalDate(String date) {
        return LocalDate.parse(date, DateTimeFormatter.ISO_DATE);
    }

    @Transactional
    public void saveAppointment(AppointmentRequestDto requestDto, String hospitalId) {
        Hospital hospital = hospitalRepository.findByHospitalId(hospitalId)
                .orElseThrow(
                        () -> new NoSuchElementException("can not found hospital. hospitalId = {%s}".formatted(hospitalId)));

        Doctor doctor = doctorRepository.findById(requestDto.getDoctorSeq())
                .orElseThrow(() -> new IllegalStateException(
                        "Can not found Doctor. doctorSeq = {%d}".formatted(requestDto.getDoctorSeq())));

        Member member = memberRepository.findById(1L).get();

        Children children = Children.builder()
                .member(member)
                .name(requestDto.getName())
                .birthDate(requestDto.getBirth())
                .gender(requestDto.getGender())
                .build();

        childrenRepository.save(children);
        Appointment appointment = AppointmentRequestDto.toEntity(requestDto, doctor, children, member, hospital);
        appointmentRepository.save(appointment);
    }
}