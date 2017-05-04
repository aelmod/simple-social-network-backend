package com.github.aelmod.ssn2;

import com.github.aelmod.ssn2.security.BadCredentialsException;
import com.github.aelmod.ssn2.security.google2fa.VerificationCodeException;
import com.github.aelmod.ssn2.user.BadUserBehaviorException;
import com.github.aelmod.ssn2.user.UserAlreadyExistsException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceException;
import javax.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class ExceptionHandlerController {

    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    @ExceptionHandler(EntityNotFoundException.class)
    public ExceptionInfo entityNotFoundHandler(HttpServletRequest request, PersistenceException e) {
        return new ExceptionInfo(request.getRequestURI(), e.getMessage());
    }

    @ResponseStatus(value = HttpStatus.FORBIDDEN)
    @ExceptionHandler(UserAlreadyExistsException.class)
    public ExceptionInfo userAlreadyExistsHandler(HttpServletRequest request, PersistenceException e) {
        return new ExceptionInfo(request.getRequestURI(), e.getMessage());
    }

    @ResponseStatus(value = HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(BadCredentialsException.class)
    public ExceptionInfo badCredentialsHandler(HttpServletRequest request, PersistenceException e) {
        return new ExceptionInfo(request.getRequestURI(), e.getMessage());
    }

    @ResponseStatus(value = HttpStatus.FORBIDDEN)
    @ExceptionHandler(BadUserBehaviorException.class)
    public ExceptionInfo badUserBehaviorHandler(HttpServletRequest request, PersistenceException e) {
        return new ExceptionInfo(request.getRequestURI(), e.getMessage());
    }

    @ResponseStatus(value = HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(VerificationCodeException.class)
    public ExceptionInfo badVerificationCodeHandler(HttpServletRequest request, PersistenceException e) {
        return new ExceptionInfo(request.getRequestURI(), e.getMessage());
    }
}
