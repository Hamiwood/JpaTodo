package com.sparta.jpatodoproject.entity;

import com.sparta.jpatodoproject.dto.UserRequestDto;
import jakarta.persistence.*;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.validation.constraints.Email;
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

    @Column(name = "username")
    @Size(min=2, max=6, message = "2자 이상 6자 이하로 작성해주세요")
    private String username;
    @Column(name = "email")
    @Pattern(regexp = "^[\\\\w!#$%&'*+/=?`{|}~^.-]+@[\\\\w.-]+\\\\.[a-zA-Z]{2,6}$", message = "이메일 형식이 올바르지 않습니다")
    private String email;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Todo> todos = new ArrayList<>();

    public User(String username, String email) {
        this.username = username;
        this.email = email;
    }

    public User(UserRequestDto reqDto) {
        this.username = reqDto.getUsername();
        this.email = reqDto.getEmail();
    }

    public void update(UserRequestDto reqDto) {
        this.username = reqDto.getUsername();
        this.email = reqDto.getEmail();
    }
}
