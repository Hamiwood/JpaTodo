package com.sparta.jpatodoproject.service;

import com.sparta.jpatodoproject.dto.CommentResponseDto;
import com.sparta.jpatodoproject.dto.TodoRequestDto;
import com.sparta.jpatodoproject.dto.TodoResponseDto;
import com.sparta.jpatodoproject.entity.Comment;
import com.sparta.jpatodoproject.entity.Todo;
import com.sparta.jpatodoproject.repository.CommentRepository;
import com.sparta.jpatodoproject.repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
public class TodoService {

    private final TodoRepository todoRepository;
    private final CommentRepository commentRepository;

    public TodoResponseDto createTodo(TodoRequestDto reqDto) {
        Todo todo = todoRepository.save(new Todo(reqDto));
        return new TodoResponseDto(todo);
    }

    public List<TodoResponseDto> showAllTodo() {
        List<Todo> todoList = todoRepository.findAll();
        List<TodoResponseDto> resDtoList = new ArrayList<>();

        for(Todo todo : todoList){
            resDtoList.add(new TodoResponseDto(todo));
        }

        return resDtoList;
    }

    public TodoResponseDto showOneTodo(int id) {

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
    public TodoResponseDto updateTodo(int id, TodoRequestDto reqDto) {
        Todo todo = todoRepository.findById(id).orElseThrow(()->
                new NullPointerException("해당 일정을 찾을 수 없습니다")
        );

        todo.update(reqDto);

        return new TodoResponseDto(todo);
    }

    @Transactional
    public String removeTodo(int id) {
        todoRepository.findById(id).orElseThrow(()->
                new NullPointerException("해당 일정을 찾을 수 없습니다")
        );

        todoRepository.deleteById(id);

        return id+"이(가) 성공적으로 삭제되었습니다";
    }
}
