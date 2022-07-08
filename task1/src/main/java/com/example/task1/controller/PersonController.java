package com.example.task1.controller;

import com.example.task1.dto.request.LoginRequest;
import com.example.task1.dto.request.ValidationRequest;
import com.example.task1.dto.response.LoginResponse;
import com.example.task1.dto.response.ValidationResponse;
import com.example.task1.exception.NicknameAlreadyExist;
import com.example.task1.exception.TooManyAuthAttemptsException;
import com.example.task1.service.PersonService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@AllArgsConstructor
@RequestMapping("/user")
public class PersonController {

    private final PersonService personService;


    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest loginRequest, HttpServletRequest request) throws TooManyAuthAttemptsException {
        return personService.login(loginRequest, request);
    }

    @PostMapping("/registration")
    public LoginResponse register(@RequestBody LoginRequest loginRequest, HttpServletRequest request) throws NicknameAlreadyExist {
        return personService.registration(loginRequest);
    }

    @PostMapping("/validation")
    public ValidationResponse validation(@RequestBody ValidationRequest request) {
        return personService.validation(request);
    }
}
