package com.sjyi.todoWebProj.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.filter.CorsFilter;

import com.sjyi.todoWebProj.security.JwtAuthenticationFilter;

import lombok.extern.slf4j.Slf4j;

@Configuration
@EnableWebSecurity
@Slf4j
public class WebSecurityConfig {
	@Autowired
	private JwtAuthenticationFilter jwtAuthenticationFilter;
	
	@Bean
	public WebSecurityCustomizer webSecurityCustomizer() {
	       return web -> {
	           web.ignoring()
	               .requestMatchers("/auth/**"); // 필터를 타면 안되는 경로 
	       };
	 }
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		
		http.authorizeHttpRequests((authorizeHttpRequests) ->
		authorizeHttpRequests
			.requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll() // resources 접근 허용 설정
			.requestMatchers("/auth/**").permitAll() 
			.anyRequest().authenticated() // 그 외 모든 요청 인증처리
	);
		
		//로그인페이지 
//		http.formLogin((formLogin) ->
//		formLogin
//			.loginPage("/api/user/login-page").permitAll()
//		);

		http.addFilterAfter(jwtAuthenticationFilter, CorsFilter.class);
		return http.build();
	}
}
