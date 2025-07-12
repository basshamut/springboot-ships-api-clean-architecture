package com.jrhub.api.domain.model;

import java.util.Objects;

public class MovieSpaceShip {
    private final Long id;
    private final String name;
    private final String movie;

    public MovieSpaceShip(Long id, String name, String movie) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be null or empty");
        }
        if (movie == null || movie.trim().isEmpty()) {
            throw new IllegalArgumentException("Movie cannot be null or empty");
        }
        
        this.id = id;
        this.name = name.trim();
        this.movie = movie.trim();
    }

    // Factory method for new entities
    public static MovieSpaceShip create(String name, String movie) {
        return new MovieSpaceShip(null, name, movie);
    }

    // Factory method for existing entities
    public static MovieSpaceShip fromPersistence(Long id, String name, String movie) {
        return new MovieSpaceShip(id, name, movie);
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getMovie() {
        return movie;
    }

    public boolean isNew() {
        return id == null;
    }

    public MovieSpaceShip withId(Long newId) {
        return new MovieSpaceShip(newId, this.name, this.movie);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MovieSpaceShip that = (MovieSpaceShip) o;
        return Objects.equals(id, that.id) &&
               Objects.equals(name, that.name) &&
               Objects.equals(movie, that.movie);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, movie);
    }

    @Override
    public String toString() {
        return "MovieSpaceShip{" +
               "id=" + id +
               ", name='" + name + '\'' +
               ", movie='" + movie + '\'' +
               '}';
    }
}
