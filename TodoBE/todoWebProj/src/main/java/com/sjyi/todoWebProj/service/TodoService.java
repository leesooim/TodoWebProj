package com.sjyi.todoWebProj.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sjyi.todoWebProj.model.TodoEntity;
import com.sjyi.todoWebProj.persistence.TodoRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class TodoService {

	@Autowired
	private TodoRepository repository;

	public String testService() {
		// TodoEntity 생성
		TodoEntity entity = TodoEntity.builder().title("My first todo item").build();
		// TodoEntity 저장
		repository.save(entity);
		// TodoEntity 검색
		TodoEntity savedEntity = repository.findById(entity.getId()).get();
		return savedEntity.getTitle();
	}

	//일정저장
	public List<TodoEntity> create(final TodoEntity entity) {

		validate(entity);

		repository.save(entity);
		log.info("Entity Id : {} is saved.", entity.getId());

		return repository.findByUserId(entity.getUserId());
	}

	//일정조회 
	public List<TodoEntity> retrieve(final String userId) {

		if(userId == null) {
			log.warn("userId cannot be null.");
			throw new RuntimeException("userId cannot be null.");
		}

		return repository.findByUserId(userId);
	}

	//일정수정 
	public List<TodoEntity> update(final TodoEntity entity) {

		validate(entity);

		final Optional<TodoEntity> original = repository.findById(entity.getId());
		original.ifPresent(todo -> {
			todo.setTitle(entity.getTitle());
			todo.setDone(entity.isDone());
			repository.save(todo);
		});

		return retrieve(entity.getUserId());
	}

	//일정삭제
	public List<TodoEntity> delete(final TodoEntity entity) {

		validate(entity);

		try {

			repository.delete(entity);

		} catch(Exception e) {

			log.error("error deleting entity ", entity.getId(), e);
			throw new RuntimeException("error deleting entity " + entity.getId());

		}

		return retrieve(entity.getUserId());
	}


	//공통 검증코드 
	private void validate(final TodoEntity entity) {

		if(entity == null) {
			log.warn("Entity cannot be null.");
			throw new RuntimeException("Entity cannot be null.");
		}

		if(entity.getUserId() == null) {
			log.warn("Unknown user.");
			throw new RuntimeException("Unknown user.");
		}

		System.out.println("Entity 확인 : "+ entity);

	}

}
