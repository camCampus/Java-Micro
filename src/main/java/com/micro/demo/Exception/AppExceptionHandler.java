package com.micro.demo.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class AppExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(NoSuchUserExistsException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public @ResponseBody ErrorResponse handleUserNotFound(NoSuchUserExistsException ex)
    {
        return new ErrorResponse(
                HttpStatus.NOT_FOUND.value(), ex.getMessage()
        );
    }
    @ExceptionHandler(UserAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.I_AM_A_TEAPOT)
    public @ResponseBody ErrorResponse handleUserAlreadyExist(UserAlreadyExistsException ex)
    {
        return new ErrorResponse(
                HttpStatus.I_AM_A_TEAPOT.value(), ex.getMessage()
        );
    }
    @ExceptionHandler(LicenseNotValidException.class)
    @ResponseStatus(HttpStatus.I_AM_A_TEAPOT)
    public @ResponseBody ErrorResponse handleLicenseNotValidException(LicenseNotValidException ex)
    {
        return new ErrorResponse(
                HttpStatus.I_AM_A_TEAPOT.value(), ex.getMessage()
        );
    }
}
