package com.salman.salapp.library.exceptions;

public class NullIdException extends AppException {

    private String fault;
    private static final long exceptionCode = 1500;

    public NullIdException(String fault) {
        super(fault, exceptionCode);
    }

    public String getFault() {
        return fault;
    }

    public long getExceptionCode() {
        return exceptionCode;
    }

}
