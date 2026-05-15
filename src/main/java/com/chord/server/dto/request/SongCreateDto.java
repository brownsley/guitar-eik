package com.chord.server.dto.request;

import java.util.List;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class SongCreateDto {

    @NotBlank(message = "Song title cannot be empty")
    @Size(min = 1, max = 150, message = "Title must be between 1 and 150 characters")
    private String title;

    @NotBlank(message = "Lyric cannot be empty")
    @Size(max = 10000, message = "Lyric is too long")
    private String lyric;

    @NotNull(message = "Album ID is required")
    private Long albumId;

    @NotEmpty(message = "At least one artist must be selected")
    private List<Long> artists;
}
