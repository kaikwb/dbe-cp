/*
 * Nomes: Kaik Wulck Bassanelli   RM: 96731
 *        Lucas Satoru Shiaku     RM: 97019
 *        Rafael Vieira Pinto     RM: 97117
 * */

package br.com.fiap.dbecp.models.api;

import br.com.fiap.dbecp.enums.Genre;
import br.com.fiap.dbecp.models.db.Movie;
import br.com.fiap.dbecp.models.db.Person;
import br.com.fiap.dbecp.models.db.PersonRepository;
import jakarta.validation.constraints.*;

import java.util.List;

public record MovieCreateRequest(
    @NotBlank
    String title,
    @NotBlank
    String mainActor,
    @NotNull
    @Min(value = 10)
    @Max(value = 240)
    Integer duration,
    @NotNull
    @Min(value = 1000)
    @Max(value = 9999)
    Integer year,
    @NotBlank
    String country,
    @NotNull
    Genre genre,
    @NotNull
    MovieCredits credits
) {
    public Movie toMovie(PersonRepository personRepository) {
        List<Person> directors = credits.directors().stream()
            .map(personRepository::findOrCreateByName)
            .toList();

        List<Person> producers = credits.producers().stream()
            .map(personRepository::findOrCreateByName)
            .toList();

        List<Person> writers = credits.writers().stream()
            .map(personRepository::findOrCreateByName)
            .toList();

        List<Person> cast = credits.cast().stream()
            .map(personRepository::findOrCreateByName)
            .toList();

        return new Movie(
            title,
            personRepository.findOrCreateByName(mainActor),
            duration,
            year,
            country,
            genre,
            credits.rating(),
            directors,
            producers,
            writers,
            cast
        );
    }
}
