package com.sjyi.todoWebProj.controller;

import com.sjyi.todoWebProj.dto.ResponseDTO;
import com.sjyi.todoWebProj.dto.TodoDTO;
import com.sjyi.todoWebProj.model.TodoEntity;
import com.sjyi.todoWebProj.service.TodoService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;

@RestController
@RequestMapping("todo")
public class TodoController {

	@Autowired
	private TodoService service;

	//	@GetMapping("toodo")
	//	public ResponseEntity<?> todoControllerResponseEntity() {
	//		String str = service.testService();
	//		List<String> list = new ArrayList<>();
	//		list.add(str);
	//		ResponseDTO<String> response = ResponseDTO.<String>builder().data(list).build();
	//
	//		return ResponseEntity.ok().body(response);
	//	}

	//일정저장 
	@PostMapping
	public ResponseEntity<?> createTodo(@RequestBody TodoDTO dto) {

		try {
			String temporaryUserId = "temporary-user"; // temporary user id.

			TodoEntity entity = TodoDTO.toEntity(dto); //TodoEntity로 변환

			entity.setId(null); //초기화
			entity.setUserId(temporaryUserId);

			List<TodoEntity> entities = service.create(entity);

			List<TodoDTO> dtos = entities.stream().map(TodoDTO::new).collect(Collectors.toList()); //자바 스트림을 이용해 리턴된 엔티티 리스트를 TodoDTO 리스트로 변환

			ResponseDTO<TodoDTO> response = ResponseDTO.<TodoDTO>builder().data(dtos).build(); //변환된 TodoDTO 리스트를 이용해 ResponseDTO를 초기화

			return ResponseEntity.ok().body(response);

		}catch(Exception e) {

			String error = e.getMessage();
			ResponseDTO<TodoDTO> response = ResponseDTO.<TodoDTO>builder().error(error).build();
			return ResponseEntity.badRequest().body(response);

		}
	}

	//일정조회 
	@GetMapping
	public ResponseEntity<?> retrieveTodoList() {

		try {
			String temporaryUserId = "temporary-user"; // temporary user id.

			List<TodoEntity> entities = service.retrieve(temporaryUserId);
			List<TodoDTO> dtos = entities.stream().map(TodoDTO::new).collect(Collectors.toList());
			ResponseDTO<TodoDTO> response = ResponseDTO.<TodoDTO>builder().data(dtos).build();

			return ResponseEntity.ok().body(response);

		}catch(Exception e) {

			String error = e.getMessage();
			ResponseDTO<TodoDTO> response = ResponseDTO.<TodoDTO>builder().error(error).build();
			return ResponseEntity.badRequest().body(response);

		}
	}

	//일정수정 
	@PutMapping
	public ResponseEntity<?> updateTodoList(@RequestBody TodoDTO dto) {

		try {
			String temporaryUserId = "temporary-user"; // temporary user id.

			TodoEntity entity = TodoDTO.toEntity(dto); //dto를 entity로 변환
			entity.setUserId(temporaryUserId); 

			List<TodoEntity> entities = service.update(entity);
			List<TodoDTO> dtos = entities.stream().map(TodoDTO::new).collect(Collectors.toList());
			ResponseDTO<TodoDTO> response = ResponseDTO.<TodoDTO>builder().data(dtos).build();

			return ResponseEntity.ok().body(response);

		}catch(Exception e) {

			String error = e.getMessage();
			ResponseDTO<TodoDTO> response = ResponseDTO.<TodoDTO>builder().error(error).build();
			return ResponseEntity.badRequest().body(response);

		}
	}

	//일정삭제
	@DeleteMapping
	public ResponseEntity<?> deleteTodo(@RequestBody TodoDTO dto) {
		try {
			String temporaryUserId = "temporary-user"; // temporary user id.

			TodoEntity entity = TodoDTO.toEntity(dto);
			entity.setUserId(temporaryUserId);

			List<TodoEntity> entities = service.delete(entity);
			List<TodoDTO> dtos = entities.stream().map(TodoDTO::new).collect(Collectors.toList());
			ResponseDTO<TodoDTO> response = ResponseDTO.<TodoDTO>builder().data(dtos).build();

			return ResponseEntity.ok().body(response);

		}catch(Exception e) {

			String error = e.getMessage();
			ResponseDTO<TodoDTO> response = ResponseDTO.<TodoDTO>builder().error(error).build();
			return ResponseEntity.badRequest().body(response);

		}
	}
	
}
