package com.chord.server.dto.request.role;

import java.util.List;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class RoleCreateDto {

    @NotBlank(message = "Role name is required")
    @Size(min = 3, max = 50)
    private String role;

    @NotEmpty(message = "At least one permission must be assigned to this role")
    private List<Long> permission;
}
