package com.tsystems.javaschool.exceptions;


public class WrongIdException extends ServiceOperationException{

    public WrongIdException() {
        super();
    }

    public WrongIdException(String message) {
        super(message);
    }
}
