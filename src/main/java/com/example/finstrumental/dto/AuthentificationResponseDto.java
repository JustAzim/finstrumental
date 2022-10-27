package com.example.finstrumental.dto;

import lombok.Data;

@Data
public class AuthentificationResponseDto {

    private UserDataDto user;
    private String token;

    public AuthentificationResponseDto(UserDataDto user, String token) {
        this.user = user;
        this.token = token;
    }
}
