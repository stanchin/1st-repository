package com.tsystems.javaschool.exceptions;


public class RequiredOptionException extends ServiceOperationException {

    public RequiredOptionException() {
        super();
    }

    public RequiredOptionException(String message) {
        super(message);
    }
}
