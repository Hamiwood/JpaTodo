package com.sparta.jpatodoproject.dto;

import com.sparta.jpatodoproject.entity.Timestamped;
import com.sparta.jpatodoproject.entity.Todo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class TodoResponseDto {
    private int id;
    private String username;
    private String title;
    private String contents;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public TodoResponseDto(Todo todo) {
        this.id = todo.getId();
        this.username = todo.getUsername();
        this.title = todo.getTitle();
        this.contents = todo.getContents();
        this.createdAt = todo.getCreatedAt();
        this.updatedAt = LocalDateTime.now();
    }
}
