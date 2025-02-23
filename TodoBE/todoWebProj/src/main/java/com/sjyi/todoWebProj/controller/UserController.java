package com.sjyi.todoWebProj.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.sjyi.todoWebProj.dto.ResponseDTO;
import com.sjyi.todoWebProj.dto.UserDTO;
import com.sjyi.todoWebProj.model.UserEntity;
import com.sjyi.todoWebProj.service.UserService;
import com.sjyi.todoWebProj.security.TokenProvider
;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/auth")
public class UserController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private TokenProvider tokenProvider;
	
	@PostMapping("/signup")
	public ResponseEntity<?> registerUser(@RequestBody UserDTO userDTO ) {
		try {
			if(userDTO == null || userDTO.getPassword() == null ) {
				throw new RuntimeException("Invalid Password value.");
			}
			
			UserEntity user = UserEntity.builder()
					.username(userDTO.getUsername())
					.password(userDTO.getPassword())
					.build();
			
			UserEntity registeredUser = userService.create(user);
			
			UserDTO responseUserDTO = UserDTO.builder()
					.username(registeredUser.getUsername())
					.id(registeredUser.getId())
					.build();
			
			return ResponseEntity.ok().body(responseUserDTO);
			
		}catch (Exception e) {
			ResponseDTO responseDTO = ResponseDTO.builder().error(e.getMessage()).build();
			return ResponseEntity
			.badRequest()
			.body(responseDTO);
		}
	}
	
	@PostMapping("/signin")
	public ResponseEntity<?> authenticate(@RequestBody UserDTO userDTO) {
		
		if (userDTO == null) {
		    log.error("userDTO is null!");
		}
		
		UserEntity user = userService.getByCredentials(
				userDTO.getUsername(),
				userDTO.getPassword());
		
		if(user != null) {
			final String token = tokenProvider.create(user);
			final UserDTO responseUserDTO = UserDTO.builder()
					.username(user.getUsername())
					.id(user.getId())
					.token(token)
					.build();
			
			return ResponseEntity.ok().body(responseUserDTO);
		}else {
			ResponseDTO responseDTO = ResponseDTO.builder()
					.error("Login failed.")
					.build();
			return ResponseEntity
			.badRequest()
			.body(responseDTO);
		}
		
	}
	
	@GetMapping
	public ResponseEntity<?> readUserList() {
		try {
			
			List<UserEntity> entities = userService.retrieve();
			List<UserDTO> dtos = entities.stream().map(UserDTO::new).collect(Collectors.toList());
			ResponseDTO<UserDTO> response = ResponseDTO.<UserDTO>builder().data(dtos).build();

			return ResponseEntity.ok().body(response);
			
		}catch (Exception e) {
			ResponseDTO responseDTO = ResponseDTO.builder().error(e.getMessage()).build();
			return ResponseEntity
			.badRequest()
			.body(responseDTO);
		}
	}
}
