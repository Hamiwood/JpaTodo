package com.sparta.jpatodoproject.entity;

import com.sparta.jpatodoproject.dto.CommentRequestDto;
import com.sparta.jpatodoproject.dto.CommentResponseDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "comment")
@NoArgsConstructor
public class Comment extends Timestamped{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "username")
    private String username;
    @Column(name = "contents")
    private String contents;

    @ManyToOne
    @JoinColumn(name = "todo_id", nullable = false)
    private Todo todo;

    public Comment(CommentRequestDto reqDto, Todo todo) {
        this.username=reqDto.getUsername();
        this.contents=reqDto.getContents();
        this.todo = todo;
    }

    public void update(CommentResponseDto reqDto) {
        this.contents=reqDto.getContents();
        this.username=reqDto.getUsername();
    }
}
