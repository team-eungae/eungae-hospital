package com.eungaehospital.security;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.eungaehospital.hospital.domain.Hospital;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

@AllArgsConstructor
@RequiredArgsConstructor
@Service
public class HospitalUserDetails implements UserDetails {

	private Hospital hospital;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Collection<GrantedAuthority> collect = new ArrayList<>();

		collect.add(new GrantedAuthority() {
			@Override
			public String getAuthority() {
				return hospital.getHospitalId();
			}
		});

		return collect;
	}

	@Override
	public String getPassword() {
		return hospital.getPassword();
	}

	@Override
	public String getUsername() {
		return hospital.getHospitalId();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
}
