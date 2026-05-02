package com.chord.server.dto.request.role;

import java.util.List;

import lombok.Data;

@Data
public class AssignRoleDto {
    private String employeeId;
    private List<Long> roles;
}
