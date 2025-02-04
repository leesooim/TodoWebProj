package com.sjyi.todoWebProj.persistence;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.sjyi.todoWebProj.model.TodoEntity;

@Repository
public interface TodoRepository extends JpaRepository<TodoEntity, UUID> {
	
	

}