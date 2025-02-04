package com.sjyi.todoWebProj.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity; //패키지명 바뀜 
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

import org.hibernate.annotations.GenericGenerator; //GenericGenerator가 hibernate 6.5버전에서 deprecated 되었다 @UuidGenerator으로 수
import org.hibernate.annotations.UuidGenerator;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "Todo")

public class TodoEntity {
	@Id //기본키 설정
	@UuidGenerator 
	@Column(name = "system-uuid", columnDefinition = "BINARY(16)",  updatable = false, nullable = false) //ID를 자동으로 생성
	
	private UUID id; // 이 오브젝트의 아이디
	private String userId; // 이 오브젝트를 생성한 유저의 아이디
	private String title; // Todo 타이틀 예) 운동 하기
	private boolean done; // true - todo를 완료한 경우(checked)
}
