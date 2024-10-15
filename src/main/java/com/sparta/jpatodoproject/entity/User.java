package com.sparta.jpatodoproject.entity;

import com.sparta.jpatodoproject.dto.UserRequestDto;
import jakarta.persistence.*;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
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
    private String username;
    @Column(name = "email")
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
}
