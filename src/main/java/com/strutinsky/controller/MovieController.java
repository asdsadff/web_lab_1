package com.strutinsky.controller;

import com.strutinsky.exception.DirectorAlreadyExistException;
import com.strutinsky.exception.MovieAlreadyExistException;
import com.strutinsky.model.Director;
import com.strutinsky.model.Movie;
import com.strutinsky.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MovieController {
    private MovieService movieService;

    @Autowired
    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping("api/movies")
    public List<Movie> getAllDirectors() {
        return movieService.getAllMovies();
    }

    @PostMapping("api/movies")
    public ResponseEntity<Movie> createMovie(@RequestBody Movie movie) {
        try {
            Movie createMovie = movieService.createMovie(movie);
            return new ResponseEntity<>(createMovie, HttpStatus.CREATED);
        } catch (MovieAlreadyExistException re) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }
}
