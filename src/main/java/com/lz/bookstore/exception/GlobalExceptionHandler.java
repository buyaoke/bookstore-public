package com.lz.bookstore.exception;

import com.lz.bookstore.common.Result;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;


@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = ServiceException.class)
    @ResponseBody
    public Result handle(ServiceException e) {
        System.out.printf(e.getMessage());
       return Result.error(e.getCode(),e.getMessage());
    }

}
