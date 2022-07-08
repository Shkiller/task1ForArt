package com.example.task1.exception;

public enum ExceptionCode {
    ANIMAL_NAME_EXIST(1),
    NICKNAME_ALREADY_EXIST(2),
    USER_NOT_OWN_ANIMAL(3),
    TOO_MANY_AUTH_ATTEMPTS(4);

    private final int code;

    ExceptionCode(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
