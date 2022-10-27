package com.example.finstrumental.controller;

import com.example.finstrumental.dto.UserDataDto;
import com.example.finstrumental.model.User;
import com.example.finstrumental.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("/api/users")
public class UserController {

    final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public ResponseEntity<UserDataDto> getUser(Authentication authentication) {

        User user = userService.findByUsername(authentication.getName());

        return new ResponseEntity<>(UserDataDto.fromUser(user), HttpStatus.OK);
    }

}
