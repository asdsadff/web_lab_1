package com.strutinsky.localStorage;

import com.strutinsky.exception.NoSuchDirectorException;
import com.strutinsky.exception.NoSuchMovieException;
import com.strutinsky.model.Director;
import com.strutinsky.model.Movie;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class Repository {
    private static List<Director> directors = new ArrayList<>();
    private static List<Movie> movies = new ArrayList<>();
    private static Map<Director, List<Movie>> directorToMovies = new HashMap<>();

    public List<Director> getDirectors() {
        return directors;
    }
    public List<Movie> getMovies() {
        return movies;
    }
    public Map<Director, List<Movie>> getMoviesByDirector() {
        return directorToMovies;
    }
    public void setDirector(Director director) {
        directors.add(director);
    }
    public void setMovie(Movie movie) {
        movies.add(movie);
    }
    public void fillMap() {
        for (Director director : directors) {
            if(!directorToMovies.containsKey(director)) {
                directorToMovies.put(director, new ArrayList<>());
            }
        }
    }
    public void deleteFromMap(long id) {
        for (Director director : directors) {
            if(director.getId() == id) {
                directorToMovies.remove(director);
            }
        }
    }
    public Director findDirectorById(long id) {
        return getDirectors().
                stream().
                filter(director -> director.getId() == id).
                findFirst().
                orElseThrow(() -> new NoSuchDirectorException("There is no such director with id " + id));
    }
    public Movie findMovieById(long id) {
        return getMovies().
                stream().
                filter(movie -> movie.getId() == id).
                findFirst().
                orElseThrow(() -> new NoSuchMovieException("There is no such movie with id " + id));
    }
    public List<Movie> findMoviesByDirector(Director director) {
        return directorToMovies.get(director);
    }
}

