package com.strutinsky.service;

import com.strutinsky.exception.DirectorAlreadyExistException;
import com.strutinsky.exception.NoSuchDirectorException;
import com.strutinsky.localStorage.Repository;
import com.strutinsky.model.Director;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
            throw new DirectorAlreadyExistException("Director already exist");
        }
        else {
            director.setId(++currentId);
            repository.setDirector(director);
            return director;
        }
    }
    public Director getDirectorById(long id) {
        return repository.
                getDirectors().
                stream().
                filter(director -> director.getId() == id).
                findFirst().
                orElseThrow(() -> new NoSuchDirectorException("There is no such director with id " + id));

    }
    public Director deleteDirector(long id) {
        Director foundedDirector = repository.
                getDirectors().
                stream().
                filter(director -> director.getId() == id).
                findFirst().
                orElseThrow(() -> new NoSuchDirectorException("There is no such director with id " + id));
        repository.getDirectors().remove(foundedDirector);
        return foundedDirector;



    }
}
