package com.sparta.jpatodoproject.service;

import com.sparta.jpatodoproject.dto.UserRequestDto;
import com.sparta.jpatodoproject.dto.UserResponseDto;
import com.sparta.jpatodoproject.entity.User;
import com.sparta.jpatodoproject.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public UserResponseDto createUser(UserRequestDto reqDto) {
        User user =  userRepository.save(new User(reqDto));

        return new UserResponseDto(user);
    }

    public List<UserResponseDto> showAllUser() {
        List<User> users = userRepository.findAll();
        List<UserResponseDto> userList = new ArrayList<>();

        for(User user : users){
            userList.add(new UserResponseDto(user));
        }

        return userList;
    }

    @Transactional
    public UserResponseDto updateUser(Long id, UserRequestDto reqDto) {
        User user = userRepository.findById(id).orElseThrow(()->
                new NullPointerException("해당 유저는 찾을 수 없습니다")
        );

        user.update(reqDto);

        return new UserResponseDto(user);
    }

    @Transactional
    public String removeUser(Long id) {
        userRepository.findById(id).orElseThrow(()->
                new NullPointerException("해당 유저는 찾을 수 없습니다")
        );

        userRepository.deleteById(id);

        return "유저"+id+"이(가) 정상적으로 삭제되었습니다.";
    }
}
