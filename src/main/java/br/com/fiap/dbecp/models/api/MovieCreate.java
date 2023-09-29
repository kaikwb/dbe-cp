package br.com.fiap.dbecp.models.api;

import br.com.fiap.dbecp.enums.Genre;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record MovieCreate(
    @NotBlank
    String title,
    @NotBlank
    String mainActor,
    @NotBlank
    @Pattern(regexp = "\\d{2,3}")
    Integer duration,
    @NotBlank
    @Pattern(regexp = "\\d{4}")
    Integer year,
    @NotBlank
    String country,
    @NotBlank
    Genre genre
    ) {
}
