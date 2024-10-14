package com.sparta.jpatodoproject.controller;

import com.sparta.jpatodoproject.dto.TodoRequestDto;
import com.sparta.jpatodoproject.dto.TodoResponseDto;
import com.sparta.jpatodoproject.service.TodoService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping
    public ResponseEntity<?> showAllTodo(@PageableDefault(size=10) Pageable pageable){
        Page<TodoResponseDto> result = todoService.showAllTodo(pageable);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/{id}")
    public TodoResponseDto showOneTodo(@PathVariable int id){
        return todoService.showOneTodo(id);
    }

    @PutMapping("/{id}")
    public TodoResponseDto updateTodo(@PathVariable int id, @RequestBody TodoRequestDto reqDto){
        return todoService.updateTodo(id, reqDto);
    }

    @DeleteMapping("/{id}")
    public String removeTodo(@PathVariable int id){
        return todoService.removeTodo(id);
    }
}
