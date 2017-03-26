package com.github.aelmod.ssn2;

import com.github.aelmod.ssn2.user.UserAlreadyExistsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletResponse;

@ControllerAdvice
public class ExceptionHandlerController {

    @ExceptionHandler(EntityNotFoundException.class)
    public void entityNotFoundHandler(HttpServletResponse response) {
        response.setStatus(HttpServletResponse.SC_NOT_FOUND);
    }

    @ExceptionHandler(UserAlreadyExistsException.class)
    public void userAlreadyExistsHandler(HttpServletResponse response, Exception e) {
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
    }
}
