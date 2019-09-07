package com.murphy1.inventory.controllers;

import com.murphy1.inventory.exceptions.BadRequestException;
import com.murphy1.inventory.exceptions.NoSuchElementException;
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
    @ExceptionHandler({NotFoundException.class, NoSuchElementException.class})
    public ModelAndView notFound(Exception exception){

        log.error("Not Found!!! "+exception.getMessage());
        ModelAndView mav = new ModelAndView();
        mav.addObject("exception", exception.getMessage());
        mav.setViewName("exceptions/404error");

        return mav;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BadRequestException.class)
    public ModelAndView badRequest(Exception exception){

        log.error("Bad Request!!! "+exception.getMessage());
        ModelAndView mav = new ModelAndView();
        mav.addObject("exception", exception.getMessage());
        mav.setViewName("exceptions/400error");

        return mav;
    }

}
