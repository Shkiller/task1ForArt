package com.example.task1.dto.request;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class LoginRequest {
    private String nickname;
    private String password;
}
