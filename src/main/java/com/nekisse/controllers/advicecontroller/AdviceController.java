package com.nekisse.controllers.advicecontroller;


import com.nekisse.controllers.advicecontroller.response.ErrorResponse;
import com.nekisse.exception.SendUserFileBadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Objects;

@RestControllerAdvice
public class AdviceController {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(SendUserFileBadRequestException.class)
    public ErrorResponse handler(SendUserFileBadRequestException e) {

        return new ErrorResponse(e.getMessage());
    }


    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BindException.class)
    public ErrorResponse handler(BindException e) {

        return new ErrorResponse(Objects.requireNonNull(e.getFieldError()).getDefaultMessage());
    }


}
