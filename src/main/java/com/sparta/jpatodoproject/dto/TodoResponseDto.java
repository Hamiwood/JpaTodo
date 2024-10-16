package com.sparta.jpatodoproject.dto;

import com.sparta.jpatodoproject.entity.Todo;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@NoArgsConstructor
public class TodoResponseDto {
    private Long id;
    private Long userId;
    private String title;
    private String contents;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String weather;
    private List<CommentResponseDto> comments;


    public TodoResponseDto(Todo todo){
        this.id = todo.getId();
        this.userId = todo.getUser().getId();
        this.title = todo.getTitle();
        this.contents = todo.getContents();
        this.createdAt = todo.getCreatedAt();
        this.updatedAt = todo.getUpdatedAt();
        this.weather = todo.getWeather();
    }

    public TodoResponseDto(Todo todo, List<CommentResponseDto> commentList) {
        this.id = todo.getId();
        this.userId = todo.getUser().getId();
        this.title = todo.getTitle();
        this.contents = todo.getContents();
        this.createdAt = todo.getCreatedAt();
        this.updatedAt = todo.getUpdatedAt();
        this.weather = todo.getWeather();
        this.comments = commentList;
    }

    public TodoResponseDto(Todo todo, Long id) {
        this.id = todo.getId();
        this.userId = id;
        this.title = todo.getTitle();
        this.contents = todo.getContents();
        this.createdAt = todo.getCreatedAt();
        this.updatedAt = LocalDateTime.now();
        this.weather = todo.getWeather();
    }
}
