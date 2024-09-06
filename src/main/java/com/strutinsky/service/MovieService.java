package com.strutinsky.service;

import com.strutinsky.localStorage.Repository;
import com.strutinsky.model.Director;
import com.strutinsky.model.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieService {
    private static long currentId = 0;
    private static Repository repository;
    @Autowired
    public MovieService(Repository repository){
        this.repository = repository;
    }
    public List<Movie> getAllMovies() {
        return repository.getMovies();
    }

    public Movie createMovie(Movie movie) {
        movie.setId(++currentId);
        repository.setMovies(movie);
        return movie;
    }
}
