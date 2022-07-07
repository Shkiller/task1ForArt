package com.example.task1.controller;

import com.example.task1.dto.ErrorDTO;
import com.example.task1.exception.AnimalNameExistException;
import com.example.task1.exception.NicknameAlreadyExist;
import com.example.task1.exception.UserNotOwnAnimalException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionController {
    @ExceptionHandler(AnimalNameExistException.class)
    public ResponseEntity<ErrorDTO> handleAnimalNameExistException(AnimalNameExistException exc) {
        ErrorDTO errorDTO = new ErrorDTO();
        errorDTO.setDescription("Ошибка 1: Такое имя животного занято");
        return new ResponseEntity<>(errorDTO, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NicknameAlreadyExist.class)
    public ResponseEntity<ErrorDTO> handleNicknameAlreadyExist(NicknameAlreadyExist exc) {
        ErrorDTO errorDTO = new ErrorDTO();
        errorDTO.setDescription("Ошибка 2: Такое никнейм аккаунта уже занят");
        return new ResponseEntity<>(errorDTO, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UserNotOwnAnimalException.class)
    public ResponseEntity<ErrorDTO> handleUserNotOwnAnimalException(UserNotOwnAnimalException exc) {
        ErrorDTO errorDTO = new ErrorDTO();
        errorDTO.setDescription("Ошибка 3: Вы не можете изменять это животное, так как оно не принадлежит вам");
        return new ResponseEntity<>(errorDTO, HttpStatus.BAD_REQUEST);
    }
}
