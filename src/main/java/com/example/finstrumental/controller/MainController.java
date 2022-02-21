package com.example.finstrumental.controller;

import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.ui.*;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/")
public class MainController {
    @Value("${spring.profiles.active}")
    private String profile;

    @GetMapping()
    public String main(Model model) {
        model.addAttribute("isDevMode", "dev".equals(profile));
        return "index";
    }
}
