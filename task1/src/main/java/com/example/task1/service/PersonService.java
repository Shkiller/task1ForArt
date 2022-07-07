package com.example.task1.service;

import com.example.task1.dto.request.LoginRequest;
import com.example.task1.dto.request.ValidationRequest;
import com.example.task1.dto.response.LoginResponse;
import com.example.task1.dto.response.ValidationResponse;
import com.example.task1.exception.NicknameAlreadyExist;


public interface PersonService {
    LoginResponse login(LoginRequest loginRequest);
    LoginResponse registration(LoginRequest loginRequest) throws NicknameAlreadyExist;
    ValidationResponse validation(ValidationRequest validationRequest);
}
