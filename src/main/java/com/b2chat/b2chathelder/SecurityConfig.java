package com.b2chat.b2chathelder;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
		//@formatter:off
		
    	return httpSecurity
    			.csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(request -> {
                	// secure all endpoints
                    request.anyRequest().authenticated(); 
                })
                .oauth2Login(Customizer.withDefaults())
                .build();
    	//@formatter:on
	}
}