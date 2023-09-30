/*
 * Nomes: Kaik Wulck Bassanelli   RM: 96731
 *        Lucas Satoru Shiaku     RM: 97019
 *        Rafael Vieira Pinto     RM: 97117
 * */

package br.com.fiap.dbecp.models.api;

import br.com.fiap.dbecp.models.db.Movie;
import br.com.fiap.dbecp.models.db.Person;

public record MovieCreateResponse(
    Integer id,
    String title,
    String mainAtor,
    Integer duration,
    Integer year,
    String country,
    String genre,
    MovieCredits credits
) {
    public MovieCreateResponse(Movie movie) {
        this(
            movie.getId(),
            movie.getTitle(),
            movie.getMainActor().getName(),
            movie.getDuration(),
            movie.getYear(),
            movie.getCountry(),
            movie.getGenre().name(),
            new MovieCredits(
                movie.getRating(),
                movie.getDirectors().stream().map(Person::getName).toList(),
                movie.getProducers().stream().map(Person::getName).toList(),
                movie.getWriters().stream().map(Person::getName).toList(),
                movie.getCast().stream().map(Person::getName).toList()
            )
        );
    }
}
