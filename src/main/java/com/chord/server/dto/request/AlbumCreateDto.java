package com.chord.server.dto.request;

import java.util.List;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AlbumCreateDto {
    private String name;
    private String cover;

    private List<Long> songs;

    @NotEmpty(message = "At least one artist is required")
    private List<Long> artists;
}
