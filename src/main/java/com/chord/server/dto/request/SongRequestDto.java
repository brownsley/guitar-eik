package com.chord.server.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SongRequestDto {

    @NotBlank(message = "Artist name cannot be empty")
    @Size(max = 50, message = "Artist name must not exceed 50 characters")
    private String artist;

    @NotBlank(message = "Song title cannot be empty")
    @Size(max = 50, message = "Song title must not exceed 50 characters")
    private String title;
}
