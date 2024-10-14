package com.sparta.jpatodoproject.dto;

import com.sparta.jpatodoproject.entity.Comment;
import com.sparta.jpatodoproject.entity.Todo;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class CommentResponseDto {
    private int id;
    private String username;
    private String contents;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private int todo_id;

    public CommentResponseDto(Comment comment, int todoId) {
        this.id = comment.getId();
        this.username = comment.getUsername();
        this.contents = comment.getContents();
        this.createdAt = comment.getCreatedAt();
        this.updatedAt = comment.getUpdatedAt();
        this.todo_id = todoId;
    }

}
