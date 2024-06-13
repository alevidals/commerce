package com.commerce.orders.exception;

import com.commerce.orders.dto.ErrorDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorDto> handleUserNotFoundException(UserNotFoundException ex) {
        ErrorDto errorDto = ErrorDto.builder().message(ex.getMessage()).build();

        return new ResponseEntity<>(errorDto, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ItemNotFoundException.class)
    public ResponseEntity<ErrorDto> handleItemNotFoundException(ItemNotFoundException ex) {
        ErrorDto errorDto = ErrorDto.builder().message(ex.getMessage()).build();

        return new ResponseEntity<>(errorDto, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(OrderWrongTotalAmountException.class)
    public ResponseEntity<ErrorDto> handleOrderWrongTotalAmountException(OrderWrongTotalAmountException ex) {
        ErrorDto errorDto = ErrorDto.builder().message(ex.getMessage()).build();

        return new ResponseEntity<>(errorDto, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(OrderNotFoundException.class)
    public ResponseEntity<ErrorDto> handleOrderNotFoundException(OrderNotFoundException ex) {
        ErrorDto errorDto = ErrorDto.builder().message(ex.getMessage()).build();

        return new ResponseEntity<>(errorDto, HttpStatus.NOT_FOUND);
    }
}
