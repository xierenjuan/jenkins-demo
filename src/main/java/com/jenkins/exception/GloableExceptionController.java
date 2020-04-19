package com.jenkins.exception;

import com.jenkins.exception.custom.DataException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class GloableExceptionController {

    private final static Logger logger = LoggerFactory.getLogger(GloableExceptionController.class);

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Object handle(Exception e) {
        if (e instanceof DataException) {
            return "error";
        }else {
            return "error";
        }
    }

}
