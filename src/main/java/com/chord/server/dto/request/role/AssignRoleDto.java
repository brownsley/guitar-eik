package com.chord.server.dto.request.role;

import java.util.List;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class AssignRoleDto {

    @NotBlank(message = "Employee ID is required")
    private String employeeId;

    @NotEmpty(message = "Please select at least one role to assign")
    private List<Long> roles;
}
