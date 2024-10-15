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
    private Long id;

    @Column(name ="title")
    private String title;

    @Column(name ="contents")
    private String contents;

    @OneToMany(mappedBy = "todo", cascade = CascadeType.REMOVE)
    private List<Comment> commentList = new ArrayList<>();

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "writer",
            joinColumns = @JoinColumn(name="todo_id"),
            inverseJoinColumns = @JoinColumn(name="user_id"))
    private List<User> userList = new ArrayList<>();

    public void update(TodoRequestDto reqDto) {
        this.title = reqDto.getTitle();
        this.contents = reqDto.getContents();
    }

    public Todo(String title, String contents) {
        this.title = title;
        this.contents = contents;
    }
}
