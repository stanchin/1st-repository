package com.tsystems.javaschool.controllers;

import com.tsystems.javaschool.exceptions.IncompatibleOptionException;
import com.tsystems.javaschool.exceptions.RequiredOptionException;
import com.tsystems.javaschool.exceptions.WrongIdException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class GlobalExceptionController {

    @ExceptionHandler(WrongIdException.class)
    public @ResponseBody Model handleWrongIdException(Model model, WrongIdException e){
        model.addAttribute("errorMsg", e.getMessage());

        return model;
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
