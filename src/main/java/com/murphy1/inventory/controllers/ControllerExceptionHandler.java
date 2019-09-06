package com.murphy1.inventory.controllers;

import com.murphy1.inventory.exceptions.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@ControllerAdvice
public class ControllerExceptionHandler {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    public ModelAndView notFound(Exception exception){

        log.error("Not Found!!! "+exception.getMessage());
        ModelAndView mav = new ModelAndView();
        mav.addObject("exception", exception.getMessage());
        mav.setViewName("exceptions/404error");

        return mav;
    }

}
