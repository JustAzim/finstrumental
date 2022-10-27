package com.example.finstrumental.dto;

import com.example.finstrumental.model.User;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserDataDto {

    private Long id;
    private String username;
    private String firstName;
    private String lastName;
    private String email;

    public static UserDataDto fromUser(User user) {
        UserDataDto adminUserDto = new UserDataDto();
        adminUserDto.setId(user.getId());
        adminUserDto.setUsername(user.getUsername());
        adminUserDto.setFirstName(user.getFirstName());
        adminUserDto.setLastName(user.getLastName());
        adminUserDto.setEmail(user.getEmail());
        return adminUserDto;
    }

}
