package com.sparta.jpatodoproject.entity;

import com.sparta.jpatodoproject.dto.UserRequestDto;
import jakarta.persistence.*;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="user")
@Getter
@NoArgsConstructor
public class User extends Timestamped{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username", nullable = false, unique = true)
    @Size(min=2, max=6, message = "2자 이상 6자 이하로 작성해주세요")
    private String username;

    @Column(name="password", nullable = false)
    @Pattern(regexp ="^(?=.*[a-zA-Z])(?=.*[~!@#$%^&*+=()_-])(?=.*[0-9]).+$", message = "영문, 숫자, 특수기호를 포함한 비밀번호를 생성하십시오")
    private String password;

    @Column(name = "email", nullable = false, unique = true)
    @Pattern(regexp = "^[a-zA-Z0-9+-\\_.]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$", message = "이메일 형식이 올바르지 않습니다")
    private String email;

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private UserRoleEnum role;


    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Todo> todos = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Comment> comments = new ArrayList<>();


    public User(UserRequestDto reqDto) {
        this.username = reqDto.getUsername();
        this.email = reqDto.getEmail();
    }

    public void update(UserRequestDto reqDto) {
        this.username = reqDto.getUsername();
        this.email = reqDto.getEmail();
    }

    public void savePassword(String password) {
        this.password = password;
    }

    public User(String username, String email, UserRoleEnum role) {
        this.username = username;
        this.email = email;
        this.role = role;
    }
}
