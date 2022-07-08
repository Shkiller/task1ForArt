package com.example.task1.service.impl;

import com.example.task1.dto.request.LoginRequest;
import com.example.task1.dto.request.ValidationRequest;
import com.example.task1.dto.response.LoginResponse;
import com.example.task1.dto.response.ValidationResponse;
import com.example.task1.entity.Auth;
import com.example.task1.entity.Person;
import com.example.task1.exception.NicknameAlreadyExist;
import com.example.task1.exception.TooManyAuthAttemptsException;
import com.example.task1.repository.AuthRepository;
import com.example.task1.repository.PersonRepository;
import com.example.task1.service.PersonService;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@Service
public class PersonServiceImpl implements PersonService {

    private final int MAX_AUTH_ATTEMPT = 10;
    private final int ATTEMPT_DELAY = 1;

    private final PersonRepository personRepository;
    private final AuthenticationManager authenticationManager;
    private final AuthRepository authRepository;

    @Override
    public LoginResponse login(LoginRequest loginRequest, HttpServletRequest request) throws TooManyAuthAttemptsException {

        //Check for Auth attempt
        List<Auth> auths = authRepository.findByIpAndTimestamp(request.getRemoteAddr(), LocalDateTime.now().minusHours(ATTEMPT_DELAY));
        if (auths.size() >= MAX_AUTH_ATTEMPT) {
            throw new TooManyAuthAttemptsException();
        }
        Authentication auth;
        try {
            auth = authenticationManager
                    .authenticate(
                            new UsernamePasswordAuthenticationToken(loginRequest.getNickname(), loginRequest.getPassword()));
            auths = authRepository.findByIp(request.getRemoteAddr());
            authRepository.deleteAll(auths);
        } catch (Exception e) {
            authRepository.save(new Auth()
                    .setIp(request.getRemoteAddr())
                    .setTimestamp(LocalDateTime.now()));
            throw new UsernameNotFoundException("user" + loginRequest.getNickname() + " " + "not found");

        }
        SecurityContextHolder.getContext().setAuthentication(auth);
        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setNickname(loginRequest.getNickname());
        return loginResponse;
    }

    @Override
    public LoginResponse registration(LoginRequest loginRequest) throws NicknameAlreadyExist {
        Person user = new Person();
        if (personRepository.findByNickname(loginRequest.getNickname()).isPresent())
            throw new NicknameAlreadyExist();
        user.setNickname(loginRequest.getNickname())
                .setPassword(new BCryptPasswordEncoder().encode(loginRequest.getPassword()));
        personRepository.save(user);

        //User authenticate after registration
        Authentication auth = authenticationManager
                .authenticate(
                        new UsernamePasswordAuthenticationToken(loginRequest.getNickname(), loginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(auth);
        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setNickname(loginRequest.getNickname());
        return loginResponse;
    }

    @Override
    public ValidationResponse validation(ValidationRequest validationRequest) {
        ValidationResponse validationResponse = new ValidationResponse();
        validationResponse.setStatus(personRepository.findByNickname(validationRequest.getNickname()).isEmpty());
        return validationResponse;
    }


}
