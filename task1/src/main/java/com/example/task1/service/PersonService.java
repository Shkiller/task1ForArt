package com.example.task1.service;

import com.example.task1.dto.request.LoginRequest;
import com.example.task1.dto.request.ValidationRequest;
import com.example.task1.dto.response.LoginResponse;
import com.example.task1.dto.response.ValidationResponse;
import com.example.task1.exception.NicknameAlreadyExist;
import com.example.task1.exception.TooManyAuthAttemptsException;

import javax.servlet.http.HttpServletRequest;


public interface PersonService {
    LoginResponse login(LoginRequest loginRequest, HttpServletRequest request) throws TooManyAuthAttemptsException;
    LoginResponse registration(LoginRequest loginRequest) throws NicknameAlreadyExist;
    ValidationResponse validation(ValidationRequest validationRequest);
}
