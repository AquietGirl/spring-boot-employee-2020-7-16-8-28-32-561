package com.thoughtworks.springbootemployee.exception;

public class NotFoundException extends RuntimeException {

    public NotFoundException(String msg){
        super(msg);
    }
}
