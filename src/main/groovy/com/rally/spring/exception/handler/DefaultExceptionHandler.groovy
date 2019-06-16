package com.rally.spring.exception.handler


import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

import javax.validation.ConstraintViolationException

@ControllerAdvice
class DefaultExceptionHandler {

    @ExceptionHandler(value = [Exception.class, ConstraintViolationException.class])
    ResponseEntity<String> handleException(Exception ex) {
        ResponseEntity responseEntity = new ResponseEntity("Please try again later", HttpStatus.INTERNAL_SERVER_ERROR)
        responseEntity
    }

}
