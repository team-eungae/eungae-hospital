package com.eungaehospital.security;

import java.util.Optional;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.eungaehospital.hospital.domain.Hospital;
import com.eungaehospital.hospital.repository.HospitalRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class HospitalUserDetailsService implements UserDetailsService {

	private final HospitalRepository hospitalRepository;

	@Override
	public UserDetails loadUserByUsername(String hospitalId) throws UsernameNotFoundException {
		Optional<Hospital> hospitalUser = hospitalRepository.findByHospitalId(hospitalId);

		if (hospitalUser.isEmpty()) {
			throw new UsernameNotFoundException(hospitalId);
		}

		return User.builder()
			.username(hospitalUser.get().getHospitalId())
			.password(hospitalUser.get().getPassword())
			.build();
	}

}
