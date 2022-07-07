package com.example.task1.controller;

import com.example.task1.dto.request.LoginRequest;
import com.example.task1.dto.request.ValidationRequest;
import com.example.task1.dto.response.LoginResponse;
import com.example.task1.dto.response.ValidationResponse;
import com.example.task1.exception.NicknameAlreadyExist;
import com.example.task1.service.PersonService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/user")
public class PersonController {

    private final PersonService personService;


    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest loginRequest) {
        return personService.login(loginRequest);
    }

    @PostMapping("/registration")
    public LoginResponse register(@RequestBody LoginRequest request) throws NicknameAlreadyExist {
        return personService.registration(request);
    }

    @PostMapping("/validation")
    public ValidationResponse validation(@RequestBody ValidationRequest request) {
        return personService.validation(request);
    }
}
