package com.chord.server.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SuggestionDto {

    @NotBlank(message = "Subject cannot be empty")
    @Size(max = 100)
    private String subject;

    @NotBlank(message = "Description cannot be empty")
    @Size(max = 500)
    private String description;
}
