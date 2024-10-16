package com.sparta.jpatodoproject.controller;

import com.sparta.jpatodoproject.dto.CommentRequestDto;
import com.sparta.jpatodoproject.dto.CommentResponseDto;
import com.sparta.jpatodoproject.service.CommentService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/comment")
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("/{todoId}")
    public CommentResponseDto createComment(@PathVariable Long todoId, @RequestBody CommentRequestDto reqDto, HttpServletRequest httpreq) {
        return commentService.createComment(todoId, reqDto, httpreq);
    }

    @PutMapping("/{todoId}/{id}")
    public CommentResponseDto updateComment(@PathVariable Long todoId, @PathVariable Long id, @RequestBody CommentResponseDto reqDto, HttpServletRequest httpreq){
        return commentService.updateComment(todoId, id, reqDto, httpreq);
    }

    @DeleteMapping("/{id}")
    public String deleteComment(@PathVariable Long id, HttpServletRequest httpreq) {
        return commentService.removeComment(id, httpreq);
    }
}
