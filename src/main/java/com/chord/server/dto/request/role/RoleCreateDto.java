package com.chord.server.dto.request.role;

import java.util.List;

import lombok.Data;

@Data
public class RoleCreateDto {
    private String role;

    private List<Long> permission;
}
