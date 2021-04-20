package com.meli.desafioquality.controller;

import com.meli.desafioquality.dtos.MessageErrorDTO;
import com.meli.desafioquality.exception.ApiException;
import com.meli.desafioquality.util.ValidateConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@ControllerAdvice(annotations = RestController.class)
public class ApiExceptionController {

    @ExceptionHandler(ApiException.class)
    public ResponseEntity exceptionHandler(ApiException e) {
        return new ResponseEntity(new MessageErrorDTO(HttpStatus.NOT_FOUND.value(), ValidateConfiguration.STATUS_ERROR.getProperty(),e.getMessage()),HttpStatus.BAD_REQUEST);
    }
}
