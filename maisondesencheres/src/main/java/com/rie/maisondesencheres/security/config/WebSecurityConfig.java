package com.rie.maisondesencheres.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.rie.maisondesencheres.baseuser.BaseUserService;

import lombok.AllArgsConstructor;

@Configuration
@AllArgsConstructor
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	private final BaseUserService baseuser_service;
	private final BCryptPasswordEncoder bcrypt_password_encoder;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.csrf().disable()
			.authorizeRequests()
				.antMatchers("/api/v*/registration/**")
				.permitAll()
			.anyRequest()
			.authenticated().and()
			.formLogin();
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(daoAuthenticationProvider());
	}
	
	@Bean
	public DaoAuthenticationProvider daoAuthenticationProvider() {
		DaoAuthenticationProvider p = new DaoAuthenticationProvider();
		p.setPasswordEncoder(bcrypt_password_encoder);
		p.setUserDetailsService(baseuser_service);
		return p;
	}

}
