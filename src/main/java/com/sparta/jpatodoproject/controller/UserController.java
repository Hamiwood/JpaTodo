package com.sparta.jpatodoproject.controller;

import com.sparta.jpatodoproject.dto.UserRequestDto;
import com.sparta.jpatodoproject.dto.UserResponseDto;
import com.sparta.jpatodoproject.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public UserResponseDto createUser(@RequestBody UserRequestDto reqDto) {
        return userService.createUser(reqDto);
    }

    @GetMapping
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
}
