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
import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Slf4j(topic="WEATHER API")
@Service
@RequiredArgsConstructor
public class TodoService {

    private final TodoRepository todoRepository;
    private final CommentRepository commentRepository;
    private final RestTemplate restTemplate;

    public TodoResponseDto createTodo(WriterRequestDto reqDto, HttpServletRequest httpreq) {

        User user = (User)httpreq.getAttribute("user");
        if(user == null) {
            throw new IllegalArgumentException("로그인 후 이용하실 수 있습니다");
        }

        String weatherInfo = getWeatherFromApi();

        Todo todo = new Todo(reqDto.getTitle(),reqDto.getContents());
        todo.setUser(user);
        todo.setWeather(weatherInfo);
        todo.getUserList().add(user);

        todoRepository.save(todo);

        return new TodoResponseDto(todo, user.getId());
    }

    private String getWeatherFromApi() {
        String weatherUrl = "https://f-api.github.io/f-api/weather.json";

        // RestTemplate으로 외부 API 호출
        ResponseEntity<String> response = restTemplate.getForEntity(weatherUrl, String.class);

        if (response.getStatusCode() == HttpStatus.OK) {

            JSONArray json = new JSONArray(response.getBody());
            String today = LocalDate.now().format(DateTimeFormatter.ofPattern("MM-dd"));

            for(int i = 0; i < json.length(); i++) {
                JSONObject jsonObject = json.getJSONObject(i);

                // 'date' 필드가 오늘 날짜와 일치하면 해당 'weather' 값 반환
                if (jsonObject.getString("date").equals(today)) {
                    return jsonObject.getString("weather");
                }
            }
            throw new IllegalArgumentException("해당 날짜의 날씨 정보를 찾을 수 없습니다.");
        } else {
            throw new IllegalArgumentException("날씨 정보를 가져오는 데 실패했습니다.");
        }
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
            resDtoList.add(new CommentResponseDto(comment));
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

        return new TodoResponseDto(todo, authenticatedUser.getId());
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
