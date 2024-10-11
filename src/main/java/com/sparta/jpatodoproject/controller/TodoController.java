package com.sparta.jpatodoproject.controller;

import com.sparta.jpatodoproject.dto.TodoRequestDto;
import com.sparta.jpatodoproject.dto.TodoResponseDto;
import com.sparta.jpatodoproject.service.TodoService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/todo")
public class TodoController {

    private final TodoService todoService;

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @PostMapping
    public TodoResponseDto createTodo(@RequestBody TodoRequestDto reqDto){
        return todoService.createTodo(reqDto);
    }
}
