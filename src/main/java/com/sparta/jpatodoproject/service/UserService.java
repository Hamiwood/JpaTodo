package com.sparta.jpatodoproject.service;

import com.sparta.jpatodoproject.dto.UserRequestDto;
import com.sparta.jpatodoproject.dto.UserResponseDto;
import com.sparta.jpatodoproject.entity.User;
import com.sparta.jpatodoproject.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public UserResponseDto createUser(UserRequestDto reqDto) {
        User user =  userRepository.save(new User(reqDto));

        return new UserResponseDto(user);
    }
}
