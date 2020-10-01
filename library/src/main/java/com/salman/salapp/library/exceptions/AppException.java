package com.salman.salapp.library.exceptions;

import lombok.Getter;

@Getter
public class AppException extends RuntimeException {

    private String faultMessage;
    private long exceptionCode;

    public AppException(String faultMessage, long exceptionCode) {
        super(faultMessage);
        this.faultMessage = faultMessage;
        this.exceptionCode = exceptionCode;
    }

    public AppException(Exception e) {
        super(e);
    }

    public AppException(String faultMessage) {
        super(faultMessage);
    }
}
