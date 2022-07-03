package com.sanjay.api.handlers;

import lombok.Getter;

@Getter
public class ExceptionMessage {

    private String message;

    public ExceptionMessage(String message) {
        this.message = message;
    }

}
