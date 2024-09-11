package com.strutinsky.controller;

import com.strutinsky.exception.MovieAlreadyExistException;
import com.strutinsky.exception.NoSuchMovieException;
import com.strutinsky.model.Movie;
import com.strutinsky.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MovieController {
    private MovieService movieService;

    @Autowired
    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping("api/movies")
    public List<Movie> getAllMovies() {
        return movieService.getAllMovies();
    }

    @PostMapping("api/movies")
    public ResponseEntity<?> createMovie(@RequestBody Movie movie) {
        try {
            Movie createdMovie = movieService.createMovie(movie);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body("Movie " +
                            createdMovie.getTitle() +
                            " with genre " +
                            createdMovie.getGenre() +
                            " has been created with id: " +
                            createdMovie.getId());
        } catch (MovieAlreadyExistException er) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(er.getMessage());
        }
    }
    @GetMapping("api/movies/{id}")
    public ResponseEntity<?> getMovieById(@PathVariable("id") long id) {
        try {
            Movie movie = movieService.getMovieById(id);
            return ResponseEntity.status(HttpStatus.FOUND).body(movie);
        } catch (NoSuchMovieException er) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(er.getMessage());
        }
    }
    @DeleteMapping("api/movies/{id}")
    public ResponseEntity<?> deleteMovie(@PathVariable("id") long id) {
        try {
            Movie deletedMovie = movieService.deleteMovie(id);
            return ResponseEntity.status(HttpStatus.OK)
                    .body("Movie " +
                            deletedMovie.getTitle() +
                            " with genre " +
                            deletedMovie.getGenre() +
                            " has been deleted with id: " +
                            deletedMovie.getId());
        } catch (MovieAlreadyExistException er) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(er.getMessage());
        } catch (NoSuchMovieException er) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(er.getMessage());
        }
    }

    @PutMapping("api/movies/{id}")
    public ResponseEntity<?> updateMovie(@RequestBody Movie movie, @PathVariable("id") long id) {
        System.out.println(movie.getTitle());
        try {
            Movie updatedMovie = movieService.updateMovie(movie, id);
            return ResponseEntity.status(HttpStatus.OK).body(updatedMovie);
        } catch (MovieAlreadyExistException er) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(er.getMessage());
        } catch (NoSuchMovieException er) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(er.getMessage());
        }
    }
}
