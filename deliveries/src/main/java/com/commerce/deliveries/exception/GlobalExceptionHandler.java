package com.commerce.deliveries.exception;

import com.commerce.deliveries.dto.ErrorDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(OrderNotFoundException.class)
    public ResponseEntity<ErrorDto> handleOrderNotFoundException(OrderNotFoundException ex) {
        ErrorDto errorDto = ErrorDto.builder().message(ex.getMessage()).build();

        return new ResponseEntity<>(errorDto, HttpStatus.NOT_FOUND);
    }
}
