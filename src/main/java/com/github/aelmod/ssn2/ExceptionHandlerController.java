package com.github.aelmod.ssn2;

import com.github.aelmod.ssn2.user.UserAlreadyExistsException;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceException;
import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ExceptionHandlerController {

    @ResponseBody
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    @ExceptionHandler(EntityNotFoundException.class)
    public ErrorInfo entityNotFoundHandler(HttpServletRequest request, PersistenceException e) {
        return new ErrorInfo(request.getRequestURI(), e.getMessage());
    }

    @ResponseBody
    @ResponseStatus(value = HttpStatus.FORBIDDEN)
    @ExceptionHandler(UserAlreadyExistsException.class)
    public ErrorInfo userAlreadyExistsHandler(HttpServletRequest request, PersistenceException e) {
        return new ErrorInfo(request.getRequestURI(), e.getMessage());
    }

    @ResponseBody
    @ResponseStatus(value = HttpStatus.FORBIDDEN)
    @ExceptionHandler(BadCredentialsException.class)
    public ErrorInfo badCredentialsHandler(HttpServletRequest request, PersistenceException e) {
        return new ErrorInfo(request.getRequestURI(), e.getMessage());
    }
}
