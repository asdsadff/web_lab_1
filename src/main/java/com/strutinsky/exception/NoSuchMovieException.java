package com.strutinsky.exception;

public class NoSuchMovieException extends RuntimeException {
    public NoSuchMovieException(String message) {
        super(message);
    }
}
