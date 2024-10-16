package com.sparta.jpatodoproject.controller;

import com.sparta.jpatodoproject.dto.TodoRequestDto;
import com.sparta.jpatodoproject.dto.TodoResponseDto;
import com.sparta.jpatodoproject.dto.WriterRequestDto;
import com.sparta.jpatodoproject.service.TodoService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/todo")
public class TodoController {

    private final TodoService todoService;

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @PostMapping
    public TodoResponseDto createTodo(@RequestBody WriterRequestDto reqDto, HttpServletRequest httpreq){
        return todoService.createTodo(reqDto, httpreq);
    }

    @GetMapping
    public ResponseEntity<?> showAllTodo(@PageableDefault(size=10) Pageable pageable){
        Page<TodoResponseDto> result = todoService.showAllTodo(pageable);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/{id}")
    public TodoResponseDto showOneTodo(@PathVariable Long id){
        return todoService.showOneTodo(id);
    }

    @PutMapping("/{id}")
    public TodoResponseDto updateTodo(@PathVariable Long id, @RequestBody TodoRequestDto reqDto, HttpServletRequest httpreq){
        return todoService.updateTodo(id, reqDto, httpreq);
    }

    @DeleteMapping("/{id}")
    public String removeTodo(@PathVariable Long id, HttpServletRequest httpreq){
        return todoService.removeTodo(id, httpreq);
    }
}
