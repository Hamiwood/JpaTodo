package com.sparta.jpatodoproject.exception;

public class NotAdminException extends RuntimeException{
    public NotAdminException(String message) {
        super(message);
    }
}