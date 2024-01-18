package com.eungaehospital.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;

import com.eungaehospital.security.HospitalUserDetailsService;

import jakarta.servlet.DispatcherType;
import lombok.RequiredArgsConstructor;

@EnableWebSecurity
@RequiredArgsConstructor
@Configuration
@Order(2)
public class HospitalUserWebSecurityConfig {
	private final HospitalUserDetailsService hospitalUserDetailsService;

	@Bean
	public PasswordEncoder hospitalUserPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public DaoAuthenticationProvider hospitalUserAuthenticationProvider() {
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setUserDetailsService(hospitalUserDetailsService);
		provider.setPasswordEncoder(hospitalUserPasswordEncoder());
		return provider;
	}

	@Bean
	public SecurityFilterChain hospitalUserFilterChain(HttpSecurity http,
		HandlerMappingIntrospector introspector) throws
		Exception {
		http.csrf(AbstractHttpConfigurer::disable)
			.authenticationProvider(hospitalUserAuthenticationProvider())
			.authorizeHttpRequests(authorizationRequests -> authorizationRequests
					.dispatcherTypeMatchers(DispatcherType.FORWARD).permitAll()
					.requestMatchers(new MvcRequestMatcher(introspector, "/hospital/admin/login")).permitAll()
					.requestMatchers(new MvcRequestMatcher(introspector, "/hospital/admin/login-proc")).permitAll()
					.requestMatchers(new MvcRequestMatcher(introspector, "/hospital/admin/login/error")).permitAll()

					.anyRequest().permitAll()
				// .anyRequest().authenticated()
			)
			.formLogin(login -> login
				.loginPage("/hospital/admin/login")
				.loginProcessingUrl("/hospital/admin/login-proc")
				.usernameParameter("hospitalId")
				.passwordParameter("password")
				.defaultSuccessUrl("/hospital/admin/main")
				.failureUrl("/hospital/admin/login/error"))
			.logout(logout -> logout
				.logoutUrl("/logout")
				.logoutSuccessUrl("/hospital/admin/login"));
		return http.build();
	}

	@Bean
	public WebSecurityCustomizer hospitalWebSecurityCustomizer() {
		return (web) -> web.ignoring()
			.requestMatchers(new AntPathRequestMatcher("/h2-console/**"))
			.requestMatchers(new AntPathRequestMatcher("/img/**"))
			.requestMatchers(new AntPathRequestMatcher("/css/**"))
			.requestMatchers(new AntPathRequestMatcher("/js/**"))
			.requestMatchers(new AntPathRequestMatcher("/lib/**"));
	}

}
