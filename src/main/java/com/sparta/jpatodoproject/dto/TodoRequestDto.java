package com.sparta.jpatodoproject.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TodoRequestDto {
    private String username;
    private String title;
    private String contents;
}
