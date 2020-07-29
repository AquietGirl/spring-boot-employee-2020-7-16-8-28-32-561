package com.thoughtworks.springbootemployee.advice;

import com.thoughtworks.springbootemployee.exception.AddWrongException;
import com.thoughtworks.springbootemployee.exception.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalControllerExptionHander {


    @ExceptionHandler(AddWrongException.class)
    @ResponseStatus(HttpStatus.NOT_IMPLEMENTED)
    public void handleAddWrongException() {

    }

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public void handleNotFoundException() {

    }

}
