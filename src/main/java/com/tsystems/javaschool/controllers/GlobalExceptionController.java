package com.tsystems.javaschool.controllers;

import com.tsystems.javaschool.exceptions.IncompatibleOptionException;
import com.tsystems.javaschool.exceptions.RequiredOptionException;
import com.tsystems.javaschool.exceptions.WrongIdException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class GlobalExceptionController {

    @ExceptionHandler(WrongIdException.class)
    public ModelAndView handleWrongIdException(WrongIdException e){
        ModelAndView mav = new ModelAndView("error");
        mav.addObject("errorMsg", e.getMessage());

        return mav;
    }

    @ExceptionHandler(IncompatibleOptionException.class)
    public ModelAndView handleIncOptionException(IncompatibleOptionException e){
        ModelAndView mav = new ModelAndView("error");
        mav.addObject("errorMsg", e.getMessage());

        return mav;
    }

    @ExceptionHandler(RequiredOptionException.class)
    public ModelAndView handleReqOptionException(RequiredOptionException e){
        ModelAndView mav = new ModelAndView("error");
        mav.addObject("errorMsg", e.getMessage());

        return mav;
    }

    @ExceptionHandler(Exception.class)
    public ModelAndView handleOtherException(Exception e){
        ModelAndView mav = new ModelAndView("error");
        mav.addObject("errorMsg", e.getMessage());

        return mav;
    }
}
