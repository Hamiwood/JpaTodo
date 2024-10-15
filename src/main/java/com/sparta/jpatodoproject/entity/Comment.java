package com.sparta.jpatodoproject.entity;

import com.sparta.jpatodoproject.dto.CommentRequestDto;
import com.sparta.jpatodoproject.dto.CommentResponseDto;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
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
    private Long id;

    @Column(name = "user_id")
    private String userId;
    @Column(name = "contents")
    @Size(min = 1, max = 50, message = "1자 이상 50자 이하로 작성해 주세요")
    private String contents;

    @ManyToOne
    @JoinColumn(name = "todo_id", nullable = false)
    private Todo todo;

    public Comment(CommentRequestDto reqDto, Todo todo) {
        this.contents=reqDto.getContents();
        this.todo = todo;
    }

    public void update(CommentResponseDto reqDto) {
        this.contents=reqDto.getContents();
    }
}
