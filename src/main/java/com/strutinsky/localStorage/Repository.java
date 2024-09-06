package com.strutinsky.localStorage;

import com.strutinsky.model.Director;
import com.strutinsky.model.Movie;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class Repository {
    private static List<Director> directors = new ArrayList<>();
    private static List<Movie> movies = new ArrayList<>();

    public List<Director> getDirectors() {
        return directors;
    }
    public List<Movie> getMovies() {
        return movies;
    }
    public void setDirector(Director director) {
        directors.add(director);
    }
    public void setMovies(Movie movie) {
        movies.add(movie);
    }
}

