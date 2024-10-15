package com.sparta.jpatodoproject.controller;

import com.sparta.jpatodoproject.dto.CommentRequestDto;
import com.sparta.jpatodoproject.dto.CommentResponseDto;
import com.sparta.jpatodoproject.service.CommentService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/comment")
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("/{todoId}")
    public CommentResponseDto createComment(@PathVariable Long todoId, @RequestBody CommentRequestDto reqDto) {
        return commentService.createComment(todoId, reqDto);
    }

    @PutMapping("/{todoId}/{id}")
    public CommentResponseDto updateComment(@PathVariable Long todoId, @PathVariable Long id, @RequestBody CommentResponseDto reqDto){
        return commentService.updateComment(todoId, id, reqDto);
    }

    @DeleteMapping("/{id}")
    public String deleteComment(@PathVariable Long id) {
        return commentService.removeComment(id);
    }
}
