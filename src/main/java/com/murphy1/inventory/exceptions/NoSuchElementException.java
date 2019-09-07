package com.murphy1.inventory.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NoSuchElementException extends RuntimeException{

    public NoSuchElementException(){
        super();
    }

    public NoSuchElementException(String message){
        super(message);
    }

    public NoSuchElementException(String message, Throwable cause){
        super(message, cause);
    }

}
