package com.sanjay.api.post.exception;

public class IllegalClientArgumentException extends RuntimeException {

    private String message;

    public IllegalClientArgumentException(String message) {
        super(message);
        this.message = message;
    }

}
