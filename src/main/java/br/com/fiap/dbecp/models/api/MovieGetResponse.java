/*
 * Nomes: Kaik Wulck Bassanelli   RM: 96731
 *        Lucas Satoru Shiaku     RM: 97019
 *        Rafael Vieira Pinto     RM: 97117
 * */

package br.com.fiap.dbecp.models.api;

import br.com.fiap.dbecp.models.db.Movie;
import br.com.fiap.dbecp.models.db.Person;

import java.util.List;

public record MovieGetResponse(
    String title,
    String mainAtor,
    Integer duration,
    String genre,
    List<String> directors
) {
    public MovieGetResponse(Movie movie) {
        this(
            movie.getTitle(),
            movie.getMainActor().getName(),
            movie.getDuration(),
            movie.getGenre().name(),
            movie.getDirectors().stream().map(Person::getName).toList()
        );
    }
}
