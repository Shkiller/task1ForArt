package com.example.task1.service;

import com.example.task1.dto.request.LoginRequest;
import com.example.task1.dto.request.ValidationRequest;
import com.example.task1.dto.response.LoginResponse;
import com.example.task1.dto.response.ValidationResponse;
import com.example.task1.exception.NicknameAlreadyExist;
import com.example.task1.exception.TooManyAuthAttemptsException;

import javax.servlet.http.HttpServletRequest;


public interface PersonService {
    /**
     * method for authenticate user
     *
     * @param loginRequest - login and pass
     * @param request      - session
     * @return - nickname authentication user
     */
    LoginResponse login(LoginRequest loginRequest, HttpServletRequest request) throws TooManyAuthAttemptsException;

    /**
     * method for registration user
     *
     * @param loginRequest - login and pass
     * @return - nickname authentication user
     */
    LoginResponse registration(LoginRequest loginRequest) throws NicknameAlreadyExist;

    /**
     * method for validation nickname
     *
     * @param validationRequest - nickname for validation
     * @return - result validation
     */
    ValidationResponse validation(ValidationRequest validationRequest);
}
