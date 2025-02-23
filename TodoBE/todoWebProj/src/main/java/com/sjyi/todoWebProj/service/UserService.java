package com.sjyi.todoWebProj.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sjyi.todoWebProj.model.TodoEntity;
import com.sjyi.todoWebProj.model.UserEntity;
import com.sjyi.todoWebProj.persistence.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	public UserEntity create(final UserEntity userEntity) {
		if(userEntity == null || userEntity.getUsername() == null) {
			throw new RuntimeException("Invalid arguments");
		}
		
		final String username = userEntity.getUsername();
		if(userRepository.existsByUsername(username)) {
			log.warn("Username already exists {}", username);
			throw new RuntimeException("Username already exists");
		}
		
		return userRepository.save(userEntity);
	}
	
	public UserEntity getByCredentials(final String username, String password) {
		
		log.info("username : " , username);
		log.info("password : " , password);
		return userRepository.findByUsernameAndPassword(username, password);
	}
	
	public List<UserEntity> retrieve() {

		return userRepository.findAll();
	}
}
