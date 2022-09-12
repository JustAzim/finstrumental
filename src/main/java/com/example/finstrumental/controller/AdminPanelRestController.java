package com.example.finstrumental.controller;

import com.example.finstrumental.dto.AdminUserDto;
import com.example.finstrumental.model.User;
import com.example.finstrumental.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin/")
public class AdminPanelRestController {

    private UserService userService;

    @Autowired
    public AdminPanelRestController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("users/{id}")
    public ResponseEntity<AdminUserDto> getUser(@PathVariable(name = "id") Long id) {
        User res = userService.findById(id);

        if(res == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(AdminUserDto.fromUser(res), HttpStatus.OK);
    }
}
