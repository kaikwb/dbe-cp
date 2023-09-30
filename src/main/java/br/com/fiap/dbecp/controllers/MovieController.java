/*
 * Nomes: Kaik Wulck Bassanelli   RM: 96731
 *        Lucas Satoru Shiaku     RM: 97019
 *        Rafael Vieira Pinto     RM: 97117
 * */

package br.com.fiap.dbecp.controllers;

import br.com.fiap.dbecp.models.api.MovieCreateRequest;
import br.com.fiap.dbecp.models.api.MovieCreateResponse;
import br.com.fiap.dbecp.models.api.MovieGetResponse;
import br.com.fiap.dbecp.models.db.Movie;
import br.com.fiap.dbecp.models.db.MovieRepository;
import br.com.fiap.dbecp.models.db.PersonRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/movies")
public class MovieController {
    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private PersonRepository personRepository;

    @PostMapping
    @Transactional
    public MovieCreateResponse create(@RequestBody @Valid MovieCreateRequest movieCreateRequest) {
        Movie movie = movieCreateRequest.toMovie(personRepository);

        movieRepository.save(movie);

        return new MovieCreateResponse(movie);
    }

    @GetMapping
    public Iterable<MovieGetResponse> getAll(@PageableDefault(size = 3, sort = {"mainActor"}) Pageable page) {
        Page<Movie> movies = movieRepository.findAllByActiveTrue(page);

        return movies.stream().map(MovieGetResponse::new).toList();
    }

    @GetMapping("/{id}")
    public MovieGetResponse getById(@PathVariable Integer id) {
        Movie movie = movieRepository.findById(id).orElseThrow();

        return new MovieGetResponse(movie);
    }

    @PutMapping("/{id}")
    @Transactional
    public void update(@PathVariable Integer id, @RequestBody @Valid MovieCreateRequest movieUpdateRequest) {
        Movie movie = movieRepository.findById(id).orElseThrow();

        if (movieUpdateRequest.title() != null) {
            movie.setTitle(movieUpdateRequest.title());
        }

        if (movieUpdateRequest.mainActor() != null) {
            movie.setMainActor(personRepository.findOrCreateByName(movieUpdateRequest.mainActor()));
        }

        if (movieUpdateRequest.duration() != null) {
            movie.setDuration(movieUpdateRequest.duration());
        }

        if (movieUpdateRequest.year() != null) {
            movie.setYear(movieUpdateRequest.year());
        }

        if (movieUpdateRequest.country() != null) {
            movie.setCountry(movieUpdateRequest.country());
        }

        if (movieUpdateRequest.genre() != null) {
            movie.setGenre(movieUpdateRequest.genre());
        }

        if (movieUpdateRequest.credits() != null) {
            if (movieUpdateRequest.credits().rating() != null) {
                movie.setRating(movieUpdateRequest.credits().rating());
            }

            if (movieUpdateRequest.credits().directors() != null) {
                movie.setDirectors(movieUpdateRequest.credits().directors().stream()
                    .map(personRepository::findOrCreateByName)
                    .toList()
                );
            }

            if (movieUpdateRequest.credits().producers() != null) {
                movie.setProducers(movieUpdateRequest.credits().producers().stream()
                    .map(personRepository::findOrCreateByName)
                    .toList()
                );
            }

            if (movieUpdateRequest.credits().writers() != null) {
                movie.setWriters(movieUpdateRequest.credits().writers().stream()
                    .map(personRepository::findOrCreateByName)
                    .toList()
                );
            }

            if (movieUpdateRequest.credits().cast() != null) {
                movie.setCast(movieUpdateRequest.credits().cast().stream()
                    .map(personRepository::findOrCreateByName)
                    .toList()
                );
            }
        }

        movieRepository.save(movie);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public void delete(@PathVariable Integer id) {
        Movie movie = movieRepository.findById(id).orElseThrow();

        if (!movie.isActive()) {
            throw new RuntimeException("Movie is already deleted");
        }

        movie.setActive(false);

        movieRepository.save(movie);
    }
}
