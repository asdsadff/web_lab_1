package com.strutinsky.service;

import com.strutinsky.exception.DirectorAlreadyExistException;
import com.strutinsky.exception.NoSuchDirectorException;
import com.strutinsky.localStorage.Repository;
import com.strutinsky.model.Director;
import com.strutinsky.model.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class DirectorService {
    private static long currentId = 0;
    private static Repository repository;
    @Autowired
    public DirectorService(Repository repository){
        this.repository = repository;
    }
    public List<Director> getAllDirectors() {
        return repository.getDirectors();
    }

    public Director createDirector(Director director) {
        if(repository.getDirectors().contains(director)) {
            throw new DirectorAlreadyExistException("Director already exists");
        }
        else {
            director.setId(++currentId);
            repository.setDirector(director);
            repository.fillMap();
            return director;
        }
    }
    public Director getDirectorById(long id) {
        return repository.findDirectorById(id);
    }
    public Director deleteDirector(long id) {
        Director foundedDirector = repository.findDirectorById(id);
        repository.getDirectors().remove(foundedDirector);
        repository.deleteFromMap(id);
        return foundedDirector;
    }
    public Director updateDirector(Director director, long id) {
        Director foundedDirector = repository.findDirectorById(id);
        if(foundedDirector == null) {
            throw new NoSuchDirectorException("Director does not exist");
        } else {
            director.setId(id);
            if(!repository.getDirectors().contains(director) &&
                    !foundedDirector.equals(director) &&
                    foundedDirector.hashCode() != director.hashCode()) {
                int index = repository.getDirectors().indexOf(foundedDirector);
                List<Movie> movies = repository.getMoviesByDirector().get(foundedDirector);
                repository.deleteFromMap(id);
                repository.getDirectors().remove(foundedDirector);
                repository.getDirectors().add(index, director);
                repository.fillMap();
                repository.getMoviesByDirector().put(director, movies);
            } else {
                throw new DirectorAlreadyExistException("Director already exists");
            }
            return director;
        }
    }
}
