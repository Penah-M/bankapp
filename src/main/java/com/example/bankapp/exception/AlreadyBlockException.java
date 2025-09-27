package com.example.bankapp.exception;

public class AlreadyBlockException extends RuntimeException {
    public AlreadyBlockException(String message) {
        super(message);
    }
}
