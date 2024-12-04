package com.qrowdsy.application.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.qrowdsy.domain.exception.DomainException;
import com.qrowdsy.domain.exception.NofFoundException;
import com.qrowdsy.domain.exception.RepositoryException;

@ControllerAdvice
public class ExceptionHandlerAdvice {

    @ExceptionHandler(value = NofFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public ErrorWrapper handle(NofFoundException exception) {
        // can be more custom
        return new ErrorWrapper(
            "40401",
            "Entity not found in the DB",
            exception.detail()
        );
    }

    @ExceptionHandler(value = RepositoryException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ErrorWrapper handle(RepositoryException exception) {
        return new ErrorWrapper(
            "50001",
            "Error while executing query in the DB",
            exception.detail()
        );
    }

    @ExceptionHandler(value = DomainException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ErrorWrapper handle(DomainException exception) {
        return new ErrorWrapper(
            "50002",
            "Domain exception occurred",
            exception.detail()
        );
    }

}
