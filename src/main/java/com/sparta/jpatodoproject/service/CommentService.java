package com.sparta.jpatodoproject.service;

import com.sparta.jpatodoproject.dto.CommentRequestDto;
import com.sparta.jpatodoproject.dto.CommentResponseDto;
import com.sparta.jpatodoproject.entity.Comment;
import com.sparta.jpatodoproject.entity.Todo;
import com.sparta.jpatodoproject.entity.User;
import com.sparta.jpatodoproject.repository.CommentRepository;
import com.sparta.jpatodoproject.repository.TodoRepository;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final TodoRepository todoRepository;

    public CommentResponseDto createComment(Long todoId, CommentRequestDto reqDto, HttpServletRequest httpreq) {

        User user = (User)httpreq.getAttribute("user");
        if(user == null) {
            throw new IllegalArgumentException("로그인 후 이용하실 수 있습니다");
        }

        Todo todo = todoRepository.findById(todoId).orElseThrow(()->
                new IllegalArgumentException("해당 Id의 일정은 존재하지 않습니다")
        );

        Comment comment = new Comment(reqDto, todo);

        comment.setUser(user);
        commentRepository.save(comment);

        return new CommentResponseDto(comment, todoId, user.getId());
    }

    @Transactional
    public CommentResponseDto updateComment(Long todoId, Long id, CommentResponseDto reqDto, HttpServletRequest httpreq) {

        User user = (User)httpreq.getAttribute("user");
        if(user == null) {
            throw new IllegalArgumentException("로그인 후 이용하실 수 있습니다");
        }

        todoRepository.findById(todoId).orElseThrow(()->
                new NullPointerException("해당 일정을 찾을 수 없습니다")
        );

        Comment comment = commentRepository.findById(id).orElseThrow(()->
                new NullPointerException("해당 댓글을 찾을 수 없습니다")
        );

        if (!comment.getUser().getId().equals(user.getId())) {
            throw new IllegalArgumentException("댓글을 수정할 권한이 없습니다.");
        }

        comment.update(reqDto);

        return new CommentResponseDto(comment, todoId);
    }

    @Transactional
    public String removeComment(Long id, HttpServletRequest httpreq) {

        User user = (User)httpreq.getAttribute("user");
        if(user == null) {
            throw new IllegalArgumentException("로그인 후 이용하실 수 있습니다");
        }

        Comment comment = commentRepository.findById(id).orElseThrow(()->
                new NullPointerException("해당 댓글을 찾을 수 없습니다")
        );

        if (!comment.getUser().getId().equals(user.getId())) {
            throw new IllegalArgumentException("댓글을 삭제할 권한이 없습니다.");
        }

        commentRepository.deleteById(id);
        return "댓글"+id+"이(가) 성공적으로 삭제되었습니다";
    }

}
