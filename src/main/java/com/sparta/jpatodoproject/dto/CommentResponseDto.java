package com.sparta.jpatodoproject.dto;

import com.sparta.jpatodoproject.entity.Comment;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class CommentResponseDto {
    private Long id;
    private String userId;
    private String contents;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Long todo_id;

    public CommentResponseDto(Comment comment, Long todoId) {
        this.id = comment.getId();
        this.userId = comment.getUserId();
        this.contents = comment.getContents();
        this.createdAt = comment.getCreatedAt();
        this.updatedAt = LocalDateTime.now();
        this.todo_id = todoId;
    }
}
