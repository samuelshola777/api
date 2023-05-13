package com.pokemonreview.api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.text.ParseException;
import java.util.Date;

@ControllerAdvice
public class GlobalExceptionHander {

    @ExceptionHandler(ParseException.class)
    public ResponseEntity<ErrorObject> handPokemonNotFoundException(PokemonNotFoundException ex, WebRequest webRequest) {

        ErrorObject errorObject = new ErrorObject();

        errorObject.setStatusCode(HttpStatus.NOT_FOUND.value());
        errorObject.setTimestamp(new Date());
        errorObject.setMessage(ex.getMessage());

    return new ResponseEntity<>(errorObject, HttpStatus.NOT_FOUND);
    }
}
