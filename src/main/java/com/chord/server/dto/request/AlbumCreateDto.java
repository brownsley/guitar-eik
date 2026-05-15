package com.chord.server.dto.request;

import java.util.List;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AlbumCreateDto {

    @NotBlank(message = "Album name is required")
    @Size(max = 50, message = "Album name must not exceed 50 characters")
    private String name;

    @NotBlank(message = "Album cover URL is required")
    private String cover;

    private List<Long> songs;

    @NotEmpty(message = "At least one artist is required")
    private List<Long> artists;
}
