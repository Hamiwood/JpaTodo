package com.sparta.jpatodoproject.controller;

import com.sparta.jpatodoproject.dto.LoginRequestDto;
import com.sparta.jpatodoproject.dto.SignUpRequestDto;
import com.sparta.jpatodoproject.dto.UserRequestDto;
import com.sparta.jpatodoproject.dto.UserResponseDto;
import com.sparta.jpatodoproject.service.UserService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/get")
    public List<UserResponseDto> showAllUser() {
        return this.userService.showAllUser();
    }

    @PutMapping("/{id}")
    public UserResponseDto updateUser(@PathVariable Long id, @RequestBody UserRequestDto reqDto) {
        return this.userService.updateUser(id, reqDto);
    }

    @DeleteMapping("/{id}")
    public String removeUser(@PathVariable Long id) {
        return this.userService.removeUser(id);
    }

    @PostMapping("/signup")
    public UserResponseDto registerUser(@RequestBody SignUpRequestDto reqDto, HttpServletResponse res) {
        if(!reqDto.getPassword().matches("^(?=.*[a-zA-Z])(?=.*[~!@#$%^&*+=()_-])(?=.*[0-9]).+$")){
            throw new IllegalArgumentException("숫자, 문자, 기호를 포함한 비밀번호를 입력하십시오.");
        }
        if((reqDto.getUsername().length()>6||reqDto.getUsername().length()<2)){
            throw new IllegalArgumentException("이름은 2자 이상 6자 이하로 입력하십시오.");
        }
        if(!reqDto.getEmail().matches("^[a-zA-Z0-9+-\\_.]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$")){
            throw new IllegalArgumentException("이메일 형식이 잘못되었습니다");
        }
        return userService.signUp(reqDto, res);
    }

    @PostMapping("/login")
    public UserResponseDto loginUser(@RequestBody LoginRequestDto reqDto, HttpServletResponse res) {
        return userService.login(reqDto, res);
    }
}
