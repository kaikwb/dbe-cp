/*
 * Nomes: Kaik Wulck Bassanelli   RM: 96731
 *        Lucas Satoru Shiaku     RM: 97019
 *        Rafael Vieira Pinto     RM: 97117
 * */

package br.com.fiap.dbecp.models.db;

import br.com.fiap.dbecp.enums.Genre;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Table(name = "movies")
@Entity(name = "Movie")
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "movies_id_seq")
    @SequenceGenerator(name = "movies_id_seq", sequenceName = "movies_id_seq")
    @Column(name = "id", updatable = false)
    private Integer id;

    @Column(name = "title", nullable = false)
    private String title;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "main_actor_id", referencedColumnName = "id")
    private Person mainActor;

    @Column(name = "duration", nullable = false)
    private Integer duration;

    @Column(name = "year", nullable = false)
    private Integer year;

    @Column(name = "country", nullable = false)
    private String country;

    @Enumerated(EnumType.STRING)
    @Column(name = "genre", nullable = false)
    private Genre genre;

    @Column(name = "rating")
    private String rating;

    @Column(name = "active")
    private Boolean active;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "movie_credits_directos",
            joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "director_id")
    )
    private List<Person> directors;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "movie_credits_producers",
            joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "producer_id")
    )
    private List<Person> producers;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
        name = "movie_credits_writers",
        joinColumns = @JoinColumn(name = "movie_id"),
        inverseJoinColumns = @JoinColumn(name = "writer_id")
    )
    private List<Person> writers;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "movie_credits_cast",
            joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "actor_id")
    )
    private List<Person> cast;

    public Movie() {

    }

    public Movie(String title, Person mainActor, Integer duration, Integer year, String country, Genre genre, String rating, List<Person> directors, List<Person> producers, List<Person> writers, List<Person> cast) {
        this.title = title;
        this.mainActor = mainActor;
        this.duration = duration;
        this.year = year;
        this.country = country;
        this.genre = genre;
        this.rating = rating;
        this.directors = new ArrayList<>(directors);
        this.producers = new ArrayList<>(producers);
        this.writers = new ArrayList<>(writers);
        this.cast = new ArrayList<>(cast);
        this.active = true;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Person getMainActor() {
        return mainActor;
    }

    public void setMainActor(Person mainActor) {
        this.mainActor = mainActor;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public Boolean isActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public List<Person> getDirectors() {
        return directors;
    }

    public void setDirectors(List<Person> directors) {
        this.directors = new ArrayList<>(directors);
    }

    public List<Person> getProducers() {
        return producers;
    }

    public void setProducers(List<Person> producers) {
        this.producers = new ArrayList<>(producers);
    }

    public List<Person> getWriters() {
        return writers;
    }

    public void setWriters(List<Person> writers) {
        this.writers = new ArrayList<>(writers);
    }

    public List<Person> getCast() {
        return cast;
    }

    public void setCast(List<Person> cast) {
        this.cast = new ArrayList<>(cast);
    }
}
