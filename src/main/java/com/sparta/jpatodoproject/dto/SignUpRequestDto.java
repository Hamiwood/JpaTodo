package com.sparta.jpatodoproject.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignUpRequestDto {
    private String username;
    private String password;
    private String email;
//    private boolean admin = false;
//    private String adminToken = "";
}
