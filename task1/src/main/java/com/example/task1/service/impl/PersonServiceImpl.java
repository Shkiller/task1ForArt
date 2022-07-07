package com.example.task1.service.impl;

import com.example.task1.dto.request.LoginRequest;
import com.example.task1.dto.request.ValidationRequest;
import com.example.task1.dto.response.LoginResponse;
import com.example.task1.dto.response.ValidationResponse;
import com.example.task1.entity.Person;
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

@AllArgsConstructor
@Service
public class PersonServiceImpl implements PersonService {

    private final PersonRepository personRepository;
    private final AuthenticationManager authenticationManager;

    @Override
    public LoginResponse login(LoginRequest loginRequest) {

        Authentication auth = authenticationManager
                .authenticate(
                        new UsernamePasswordAuthenticationToken(loginRequest.getNickname(), loginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(auth);
        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setNickname(loginRequest.getNickname());
        return loginResponse;
    }

    @Override
    public LoginResponse registration(LoginRequest loginRequest) {
        Person user = new Person();
        if (personRepository.findByNickname(loginRequest.getNickname()).isPresent())
            throw new UsernameNotFoundException(loginRequest.getNickname());
        user.setNickname(loginRequest.getNickname())
                .setPassword(new BCryptPasswordEncoder().encode(loginRequest.getPassword()));
        personRepository.save(user);

        return login(loginRequest);
    }

    @Override
    public ValidationResponse validation(ValidationRequest validationRequest) {
        ValidationResponse validationResponse = new ValidationResponse();
        validationResponse.setStatus(personRepository.findByNickname(validationRequest.getNickname()).isEmpty());
        return validationResponse;
    }

}
