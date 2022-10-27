package com.example.finstrumental.controller;

import com.example.finstrumental.dto.AuthRegistrationRequestDto;
import com.example.finstrumental.dto.AuthentificationRequestDto;
import com.example.finstrumental.dto.AuthentificationResponseDto;
import com.example.finstrumental.dto.UserDataDto;
import com.example.finstrumental.model.User;
import com.example.finstrumental.security.jwt.JwtTokenProvider;
import com.example.finstrumental.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/auth/")
public class AuthentificationRestController {

    private final AuthenticationManager authenticationManager;

    private final JwtTokenProvider jwtTokenProvider;

    private final UserService userService;

    @Autowired
    public AuthentificationRestController(AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider, UserService userService) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
        this.userService = userService;
    }

    @PostMapping("login")
    public ResponseEntity login(@RequestBody AuthentificationRequestDto requestDto) {
        try {
            String username = requestDto.getUsername();
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, requestDto.getPassword()));
            User user = userService.findByUsername(username);

            if (user == null) {
                throw new UsernameNotFoundException("User with username: " + username + " not found");
            }

            String token = jwtTokenProvider.createToken(username, user.getRoles());

            return ResponseEntity.ok(new AuthentificationResponseDto(new UserDataDto().fromUser(user), token));
        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Invalid username or password");
        }
    }

    @PostMapping("register")
    public HttpStatus register(@RequestBody AuthRegistrationRequestDto requestDto) throws Exception {
        try {
            User user = new User();
            user.setUsername(requestDto.getUsername());
            user.setPassword(requestDto.getPassword());
            user.setFirstName(requestDto.getFirstName());
            user.setLastName(requestDto.getLastName());
            user.setEmail(requestDto.getEmail());
            userService.register(user);

            return HttpStatus.OK;
        }
        catch (AuthenticationException e) {
            throw new Exception(e);
        }
    }
}
