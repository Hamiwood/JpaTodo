package com.sparta.jpatodoproject.dto;

import com.sparta.jpatodoproject.entity.Todo;
import com.sparta.jpatodoproject.entity.User;
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
    private List<CommentResponseDto> comments;

    public TodoResponseDto(Todo todo) {
        this.id = todo.getId();
        this.userId = todo.getUser() != null ? todo.getUser().getId() : null;
        this.title = todo.getTitle();
        this.contents = todo.getContents();
        this.createdAt = todo.getCreatedAt();
        this.updatedAt = LocalDateTime.now();
    }

    public TodoResponseDto(Todo todo, List<CommentResponseDto> commentList) {
        this.id = todo.getId();
        this.userId = todo.getUser() != null ? todo.getUser().getId() : null;
        this.title = todo.getTitle();
        this.contents = todo.getContents();
        this.createdAt = todo.getCreatedAt();
        this.updatedAt = LocalDateTime.now();
        this.comments = commentList;
    }

    public TodoResponseDto(Todo todo, Long id) {
        this.id = todo.getId();
        this.userId = id;
        this.title = todo.getTitle();
        this.contents = todo.getContents();
        this.createdAt = todo.getCreatedAt();
        this.updatedAt = LocalDateTime.now();
    }
}
