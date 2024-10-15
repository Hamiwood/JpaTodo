package com.sparta.jpatodoproject.service;

import com.sparta.jpatodoproject.config.PasswordEncoder;
import com.sparta.jpatodoproject.dto.LoginRequestDto;
import com.sparta.jpatodoproject.dto.SignUpRequestDto;
import com.sparta.jpatodoproject.dto.UserRequestDto;
import com.sparta.jpatodoproject.dto.UserResponseDto;
import com.sparta.jpatodoproject.entity.User;
import com.sparta.jpatodoproject.entity.UserRoleEnum;
import com.sparta.jpatodoproject.jwt.JwtUtil;
import com.sparta.jpatodoproject.repository.UserRepository;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    private final String ADMIN_TOKEN = "AAABnvxRVklrnYxKZ0aHgTBcXukeZygoC";

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

    @Transactional
    public UserResponseDto signUp(SignUpRequestDto reqDto, HttpServletResponse res) {
        userRepository.findByEmail(reqDto.getEmail())
                .ifPresent(user -> {
                    throw new IllegalArgumentException("이미 존재하는 이메일입니다");
                });
        userRepository.findByUsername(reqDto.getUsername())
                .ifPresent(user -> {
                   throw new IllegalArgumentException("이미 존재하는 유저명 입니다");
                });

        UserRoleEnum role = UserRoleEnum.USER;
        if(reqDto.isAdmin()){
            if(!ADMIN_TOKEN.equals(reqDto.getAdminToken())){
                throw new IllegalArgumentException("관리자 암호가 일치하지 않습니다");
            }
            role = UserRoleEnum.ADMIN;
        }

        String encodedPassword = passwordEncoder.encode(reqDto.getPassword());
        User user = new User(reqDto.getUsername(), reqDto.getEmail(), role);
        user.savePassword(encodedPassword);

        userRepository.save(user);

//        login(new LoginRequestDto(reqDto.getEmail(), reqDto.getPassword()), res);
        return new UserResponseDto(user);
    }

    public UserResponseDto login(LoginRequestDto reqDto, HttpServletResponse res) {
        String email = reqDto.getEmail();
        String password = reqDto.getPassword();

        User user = userRepository.findByEmail(email).orElseThrow(()->
                new IllegalArgumentException("등록된 사용자가 없습니다")
        );

        if(!passwordEncoder.matches(password, user.getPassword())){
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다");
        }

        String token = jwtUtil.createToken(email, user.getRole());
        jwtUtil.addJwtToCookie(token, res);

        return new UserResponseDto(user);
    }
}
