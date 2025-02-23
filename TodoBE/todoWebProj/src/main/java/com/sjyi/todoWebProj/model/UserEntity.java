package com.sjyi.todoWebProj.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator; //GenericGenerator가 hibernate 6.5버전에서 deprecated 되었다 @UuidGenerator으로 수
import org.hibernate.annotations.UuidGenerator;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

import java.util.UUID;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = "username")})
public class UserEntity {
	@Id //기본키 설정
	@UuidGenerator 
	@Column(name = "system-uuid", columnDefinition = "BINARY(16)",  updatable = false, nullable = false) //ID를 자동으로 생성
	
	private UUID id; 
	
	@Column(nullable = false)
	private String username;
	
	private String password;
	private String role;
	private String authProvider; // 이후 OAuth에서 사용할 유저 정보 제공자 : github
	
	
}
