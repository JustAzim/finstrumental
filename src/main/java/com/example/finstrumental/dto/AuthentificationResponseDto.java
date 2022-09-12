package com.example.finstrumental.dto;

import lombok.Data;

@Data
public class AuthentificationResponseDto {

    private String username;
    private String token;

    public AuthentificationResponseDto(String username, String token) {
        this.username = username;
        this.token = token;
    }
}
