package com.sjyi.todoWebProj.dto;

import java.util.UUID;

import com.sjyi.todoWebProj.model.UserEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

	private UUID id;
	private String token;
	private String username;
	private String password;

	public UserDTO(final UserEntity entity) {
		this.id = entity.getId();
		
		this.username = entity.getUsername();
		this.password = entity.getPassword();
	}

	public static UserEntity toEntity(final UserDTO dto) {
		return UserEntity.builder()
				.id(dto.getId())
				.username(dto.getUsername())
//				.userId(dto.userId)
				.password(dto.getPassword())
				.build();
	}
}
