package com.jrhub.api.dto;

public record MovieSpaceShipsDto(
    Long id,
    String name,
    String movie
) {

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private Long id;
        private String name;
        private String movie;

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder movie(String movie) {
            this.movie = movie;
            return this;
        }

        public MovieSpaceShipsDto build() {
            return new MovieSpaceShipsDto(id, name, movie);
        }
    }
}
