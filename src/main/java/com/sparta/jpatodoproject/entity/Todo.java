package com.sparta.jpatodoproject.entity;

import com.sparta.jpatodoproject.dto.TodoRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "todo")
@NoArgsConstructor
public class Todo extends Timestamped{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name ="username")
    private String username;
    @Column(name ="title")
    private String title;
    @Column(name ="contents")
    private String contents;

    @OneToMany(mappedBy = "todo", cascade = CascadeType.REMOVE)
    private List<Comment> commentList = new ArrayList<>();

    public Todo(TodoRequestDto reqDto) {
        this.username = reqDto.getUsername();
        this.title = reqDto.getTitle();
        this.contents = reqDto.getContents();
    }

    public void update(TodoRequestDto reqDto) {
        this.username = reqDto.getUsername();
        this.title = reqDto.getTitle();
        this.contents = reqDto.getContents();
    }
}
