package com.chord.server.dto.request.music;

import org.hibernate.validator.constraints.URL;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ArtistCreateDto {

    @NotBlank(message = "Artist name is required")
    @Size(min = 2, max = 50, message = "Name must be between 2 and 50 characters")
    private String name;

    @NotBlank(message = "Avatar is required")
    private String avater;

    @URL(message = "Please provide a valid social media link")
    private String socialLink;
}
