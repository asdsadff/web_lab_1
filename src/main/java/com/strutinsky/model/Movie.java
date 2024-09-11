package com.strutinsky.model;

import lombok.*;

import java.security.Timestamp;
import java.time.Duration;
import java.time.LocalDate;
import java.util.Date;
import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Movie {
    private long id;
    private String title;
    private String genre;
    private LocalDate releaseDate;
    private Duration duration;
    private Director director;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Movie movie = (Movie) o;
        return Objects.equals(title, movie.title) && Objects.equals(genre, movie.genre) && Objects.equals(releaseDate, movie.releaseDate) && Objects.equals(duration, movie.duration) && Objects.equals(director, movie.director);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, genre, releaseDate, duration, director);
    }
}
