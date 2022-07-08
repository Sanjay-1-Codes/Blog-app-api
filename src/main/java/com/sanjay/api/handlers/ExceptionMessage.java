package com.sanjay.api.handlers;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ExceptionMessage {

    private LocalDateTime timeStamp;
    private String message;
    private String urlDescription;

    public ExceptionMessage(LocalDateTime timeStamp,String message, String urlDescription) {
        this.timeStamp = timeStamp;
        this.message = message;
        this.urlDescription = urlDescription;
    }

}
