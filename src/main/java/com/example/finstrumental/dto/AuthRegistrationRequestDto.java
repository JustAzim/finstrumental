package com.example.finstrumental.dto;

import lombok.Data;

@Data
public class AuthRegistrationRequestDto {

    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String email;

}
