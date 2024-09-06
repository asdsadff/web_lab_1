package com.strutinsky.exception;

public class DirectorAlreadyExistException extends RuntimeException {
    public DirectorAlreadyExistException(String message) {
        super(message);
    }
}
