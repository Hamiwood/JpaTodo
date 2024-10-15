package com.sparta.jpatodoproject.controller;

import com.sparta.jpatodoproject.dto.UserRequestDto;
import com.sparta.jpatodoproject.dto.UserResponseDto;
import com.sparta.jpatodoproject.repository.UserRepository;
import com.sparta.jpatodoproject.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserRepository userRepository;
    private final UserService userService;

    public UserController(UserRepository userRepository, UserService userService) {
        this.userRepository = userRepository;
        this.userService = userService;
    }

    @PostMapping
    public UserResponseDto createUser(@RequestBody UserRequestDto reqDto) {
        return userService.createUser(reqDto);
    }
}
