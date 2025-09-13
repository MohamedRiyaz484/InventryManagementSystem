package com.project.IMS.customExceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(OutOfStockException.class)
    public ModelAndView handleOutOfStockException(OutOfStockException ex) {
        ModelAndView mav = new ModelAndView("error"); // error.jsp
        mav.addObject("message", ex.getMessage());
        mav.setStatus(HttpStatus.BAD_REQUEST);
        return mav;
    }
    
    @ExceptionHandler(StockExceededException.class)
    public ModelAndView handleStockExceededException(OutOfStockException ex) {
        ModelAndView mav = new ModelAndView("error"); // error.jsp
        mav.addObject("message", ex.getMessage());
        mav.setStatus(HttpStatus.BAD_REQUEST);
        return mav;
    }
    // Handle all other exceptions
    @ExceptionHandler(Exception.class)
    public ModelAndView handleGeneralException(Exception ex) {
        ModelAndView mav = new ModelAndView("error"); // error.jsp
        mav.addObject("message", "Something went wrong: " + ex.getMessage());
        mav.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
        return mav;
    }
}
