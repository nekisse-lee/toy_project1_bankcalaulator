package com.nekisse.controllers.advicecontroller;


import com.nekisse.controllers.advicecontroller.response.ErrorResponse;
import com.nekisse.exception.SendUserFileBadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.DateTimeException;
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

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(DateTimeException.class)
    public ErrorResponse handler(DateTimeException e) {

        System.out.println("e.getMessage() = " + e.getCause());

        return new ErrorResponse("1월부터 12월 까지만 조회 가능합니다.");
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(NumberFormatException.class)
    public ErrorResponse handler(NumberFormatException e) {

        System.out.println("e.getMessage() = " + e.getCause());
        return new ErrorResponse("숫자만 가능 합니다.");
    }


}
