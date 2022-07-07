package com.example.task1.service;

import com.example.task1.dto.request.LoginRequest;
import com.example.task1.dto.request.ValidationRequest;
import com.example.task1.dto.response.LoginResponse;
import com.example.task1.dto.response.ValidationResponse;


public interface UserService {
    LoginResponse login(LoginRequest loginRequest);
    LoginResponse registration(LoginRequest loginRequest);
    ValidationResponse validation(ValidationRequest validationRequest);
}
