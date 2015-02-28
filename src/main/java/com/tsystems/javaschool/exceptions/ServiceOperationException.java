package com.tsystems.javaschool.exceptions;


public class ServiceOperationException  extends Exception{
    public ServiceOperationException() {
        super();
    }

    public ServiceOperationException(String message) {
        super(message);
    }

    public ServiceOperationException(String message, Throwable cause) {
        super(message, cause);
    }
}
