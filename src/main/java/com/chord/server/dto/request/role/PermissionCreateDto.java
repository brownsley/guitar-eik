package com.chord.server.dto.request.role;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class PermissionCreateDto {

    @NotBlank(message = "Permission name is required")
    @Size(min = 3, max = 50)
    private String permission;
}
