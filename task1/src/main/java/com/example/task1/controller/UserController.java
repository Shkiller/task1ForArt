package com.example.task1.controller;

import com.example.task1.dto.request.LoginRequest;
import com.example.task1.dto.response.LoginResponse;
import com.example.task1.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;


    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest loginRequest) {
        return userService.login(loginRequest);
    }

    @PostMapping("/register")
    public LoginResponse register(@RequestBody LoginRequest request) {
        return userService.registration(request);
    }

}
