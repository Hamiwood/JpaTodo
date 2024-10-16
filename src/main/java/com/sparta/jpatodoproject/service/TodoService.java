package com.sparta.jpatodoproject.service;

import com.sparta.jpatodoproject.dto.*;
import com.sparta.jpatodoproject.entity.Comment;
import com.sparta.jpatodoproject.entity.Todo;
import com.sparta.jpatodoproject.entity.User;
import com.sparta.jpatodoproject.entity.UserRoleEnum;
import com.sparta.jpatodoproject.repository.CommentRepository;
import com.sparta.jpatodoproject.repository.TodoRepository;
import com.sparta.jpatodoproject.repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
public class TodoService {

    private final TodoRepository todoRepository;
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;

    public TodoResponseDto createTodo(WriterRequestDto reqDto, HttpServletRequest httpreq) {

        User user = (User)httpreq.getAttribute("user");
        if(user == null) {
            throw new IllegalArgumentException("로그인 후 이용하실 수 있습니다");
        }

        Todo todo = new Todo(reqDto.getTitle(),reqDto.getContents());
        todo.setUser(user);
        todo.getUserList().add(user);

        todoRepository.save(todo);

        return new TodoResponseDto(todo, user.getId());
    }

    public Page<TodoResponseDto> showAllTodo(Pageable pageable) {
        List<Todo> todoList = todoRepository.findAll();
        List<TodoResponseDto> resDtoList = new ArrayList<>();

        for(Todo todo : todoList){
            resDtoList.add(new TodoResponseDto(todo));
        }

        int start = (int)pageable.getOffset();
        int end = Math.min((start+pageable.getPageSize()), resDtoList.size());
        return new PageImpl<>(resDtoList.subList(start, end), pageable, resDtoList.size());
    }

    public TodoResponseDto showOneTodo(Long id) {

        Todo todo = todoRepository.findById(id).orElseThrow(() ->
                new NullPointerException("해당 일정을 찾을 수 없습니다")
        );

        List<Comment> commentList = commentRepository.findByTodoId(id);
        List<CommentResponseDto> resDtoList = new ArrayList<>();

        for (Comment comment : commentList) {
            resDtoList.add(new CommentResponseDto(comment, id));
        }

        return new TodoResponseDto(todo, resDtoList);
    }

    @Transactional
    public TodoResponseDto updateTodo(Long id, TodoRequestDto reqDto, HttpServletRequest req) {

        User authenticatedUser = (User) req.getAttribute("user");

        Todo todo = todoRepository.findById(id).orElseThrow(()->
                new NullPointerException("해당 일정을 찾을 수 없습니다")
        );

        if(authenticatedUser.getRole() != UserRoleEnum.ADMIN) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "관리자만 접근이 가능한 서비스입니다");
        }

        todo.update(reqDto);

        return new TodoResponseDto(todo);
    }

    @Transactional
    public String removeTodo(Long id, HttpServletRequest req) {

        User authenticatedUser = (User) req.getAttribute("user");

        todoRepository.findById(id).orElseThrow(()->
                new NullPointerException("해당 일정을 찾을 수 없습니다")
        );

        if(authenticatedUser.getRole() != UserRoleEnum.ADMIN) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "관리자만 접근이 가능한 서비스입니다");
        }

        todoRepository.deleteById(id);

        return id+"이(가) 성공적으로 삭제되었습니다";
    }

}
