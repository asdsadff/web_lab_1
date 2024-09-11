package com.strutinsky.service;

import com.strutinsky.exception.DirectorAlreadyExistException;
import com.strutinsky.exception.MovieAlreadyExistException;
import com.strutinsky.exception.NoSuchDirectorException;
import com.strutinsky.exception.NoSuchMovieException;
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
        if(repository.getMovies().contains(movie)) {
            throw new MovieAlreadyExistException("Movie already exists");
        }
        else {
            movie.setId(++currentId);
            repository.setMovie(movie);
            if (repository.getMoviesByDirector().containsKey(movie.getDirector())) {
                repository.getMoviesByDirector().get(movie.getDirector()).add(movie);
            }
            return movie;
        }
    }

    public Movie getMovieById(long id) {
        return repository.findMovieById(id);
    }

    public Movie deleteMovie(long id) {
        Movie foundedMovie = repository.findMovieById(id);
        repository.getDirectors().remove(foundedMovie);
        return foundedMovie;
    }

    public Movie updateMovie(Movie movie, long id) {
        Movie foundedMovie = repository.findMovieById(id);
        if(foundedMovie == null) {
            throw new NoSuchMovieException("Movie does not exist");
        } else {
            movie.setId(id);
            if(!repository.getMovies().contains(movie) &&
                    !foundedMovie.equals(movie) &&
                    foundedMovie.hashCode() != movie.hashCode()) {
                int index = repository.getDirectors().indexOf(foundedMovie);
                int indexInMapList = repository.findMoviesByDirector(movie.getDirector())
                        .indexOf(foundedMovie);
                repository.findMoviesByDirector(movie.getDirector())
                                .remove(foundedMovie);
                repository.getMovies().remove(foundedMovie);
                repository.getMovies().add(index, movie);
                repository.getMovies().add(indexInMapList, movie);
            } else {
                throw new MovieAlreadyExistException("Movie already exists");
            }
            return movie;
        }
    }
}
