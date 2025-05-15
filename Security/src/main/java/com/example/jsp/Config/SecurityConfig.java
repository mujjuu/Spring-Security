package com.example.jsp.Config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	//comment this for in-memory if wannt to encrypt (use custom UserDetailsService) decomment this and comment temporary authorization userDetailsService() method and also change in dao
	@Autowired
	private UserDetailsService userDetailsService;

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		
		http
		.csrf()
		.disable()
		.authorizeRequests()
		.requestMatchers("/users/**").hasRole("ADMIN")
		.requestMatchers("/home").hasRole("USER")
		.anyRequest()
		.authenticated()
		.and()
		.formLogin();
		
		http.httpBasic();
		
		return http.build();
	}
	
	//If i want multiple users authorization -- Temporary Authorization
//	@Bean
//	public UserDetailsService userDetailsService() {
//		UserDetails user1 = User.withUsername("smith").
//				password(passwordEncoder()
//				.encode("s@123"))
//				.roles("USER")
//				.build();
//		
//		UserDetails user2 = User.withUsername("riya").
//				password(passwordEncoder()
//				.encode("r@123"))
//				.roles("ADMIN")
//				.build();
//		
//		return new InMemoryUserDetailsManager(user1,user2);
//	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public AuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
		//authenticationProvider.setPasswordEncoder(NoOpPasswordEncoder.getInstance());
		authenticationProvider.setPasswordEncoder(passwordEncoder());
		authenticationProvider.setUserDetailsService(userDetailsService);
		
		return authenticationProvider;
	}
	
}
