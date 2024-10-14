package com.sparta.jpatodoproject.service;

import com.sparta.jpatodoproject.dto.CommentRequestDto;
import com.sparta.jpatodoproject.dto.CommentResponseDto;
import com.sparta.jpatodoproject.entity.Comment;
import com.sparta.jpatodoproject.entity.Todo;
import com.sparta.jpatodoproject.repository.CommentRepository;
import com.sparta.jpatodoproject.repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final TodoRepository todoRepository;

    public CommentResponseDto createComment(int todoId, CommentRequestDto reqDto) {

        Todo todo = todoRepository.findById(todoId).orElseThrow(()->
                new IllegalArgumentException("해당 Id의 일정은 존재하지 않습니다")
        );

        Comment comment = commentRepository.save(new Comment(reqDto, todo));
        return new CommentResponseDto(comment, todoId);
    }
}
