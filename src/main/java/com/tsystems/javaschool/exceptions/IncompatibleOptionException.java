package com.tsystems.javaschool.exceptions;


public class IncompatibleOptionException extends ServiceOperationException {

    public IncompatibleOptionException(){
        super();
    }

    public IncompatibleOptionException(String message) {
        super(message);
    }
}
