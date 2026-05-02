package com.chord.server.dto.request.auth;

import lombok.Data;

@Data
public class UserLoginDto {
    private String employeeId;

    private String password;
}
