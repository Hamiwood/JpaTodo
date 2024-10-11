package com.sparta.jpatodoproject.service;

import com.sparta.jpatodoproject.dto.TodoRequestDto;
import com.sparta.jpatodoproject.dto.TodoResponseDto;
import com.sparta.jpatodoproject.entity.Todo;
import com.sparta.jpatodoproject.repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TodoService {

    private final TodoRepository todoRepository;

    public TodoResponseDto createTodo(TodoRequestDto reqDto) {
        Todo todo = todoRepository.save(new Todo(reqDto));
        return new TodoResponseDto(todo);
    }
}
