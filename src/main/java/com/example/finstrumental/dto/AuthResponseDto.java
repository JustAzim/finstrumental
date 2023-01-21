package com.example.finstrumental.dto;

import lombok.Data;

@Data
public class AuthResponseDto {

    private UserDataDto user;
    private String token;

    public AuthResponseDto(UserDataDto user, String token) {
        this.user = user;
        this.token = token;
    }
}
