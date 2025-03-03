package com.sjyi.todoWebProj.persistence;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.sjyi.todoWebProj.model.TodoEntity;

@Repository
public interface TodoRepository extends JpaRepository<TodoEntity, UUID> {
	
	// 일정조회 
	List<TodoEntity> findByUserId(String userId);

}