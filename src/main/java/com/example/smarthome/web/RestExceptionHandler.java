package com.example.smarthome.web;

import com.example.smarthome.domain.ApiErrorResponse;
import com.example.smarthome.exception.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestControllerAdvice
public class RestExceptionHandler {


    @ExceptionHandler(ApplianceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiErrorResponse handleNotFound(ApplianceNotFoundException ex) {
        return new ApiErrorResponse(ex.getMessage());
    }

    @ExceptionHandler({InvalidSpeedException.class, InvalidModeException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiErrorResponse handleBadRequest(Exception ex) {
        return new ApiErrorResponse(ex.getMessage());
    }
}
