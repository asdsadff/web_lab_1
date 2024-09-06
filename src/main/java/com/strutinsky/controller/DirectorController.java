package com.strutinsky.controller;

import com.strutinsky.exception.DirectorAlreadyExistException;
import com.strutinsky.exception.NoSuchDirectorException;
import com.strutinsky.model.Director;
import com.strutinsky.service.DirectorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DirectorController {
    private DirectorService directorService;

    @Autowired
    public DirectorController(DirectorService directorService) {
        this.directorService = directorService;
    }

    @GetMapping("api/directors")
    public List<Director> getAllDirectors() {
        return directorService.getAllDirectors();
    }

    @PostMapping("api/directors")
    public ResponseEntity<?> createDirector(@RequestBody Director director) {
        try {
            Director createdDirector = directorService.createDirector(director);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body("Director "+
                            createdDirector.getFirstName()+
                            " "+
                            createdDirector.getLastName()+
                            " has been created with id: "
                            +createdDirector.getId());
        } catch (DirectorAlreadyExistException er) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(er.getMessage());
        }
    }
    @GetMapping("api/directors/{id}")
    public ResponseEntity<?> getDirectorById(@PathVariable("id") long id) {
        try {
            Director director = directorService.getDirectorById(id);
            return ResponseEntity.status(HttpStatus.FOUND).body(director);
        } catch (NoSuchDirectorException er) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(er.getMessage());
        }

    }
    @DeleteMapping("api/directors/{id}")
    public ResponseEntity<?> deleteDirector(@PathVariable("id") long id) {
        try {
            Director deletedDirector = directorService.deleteDirector(id);
            return ResponseEntity.status(HttpStatus.OK)
                    .body("Director "+
                            deletedDirector.getFirstName()+
                            " "+
                            deletedDirector.getLastName()+
                            " has been deleted with id: "
                            +deletedDirector.getId());
        } catch (DirectorAlreadyExistException er) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(er.getMessage());
        }
    }
}
