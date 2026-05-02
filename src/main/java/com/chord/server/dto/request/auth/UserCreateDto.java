package com.chord.server.dto.request.auth;

import lombok.Data;

@Data

public class UserCreateDto {
    private String username;

    private String employeeId;

    private String password;
}