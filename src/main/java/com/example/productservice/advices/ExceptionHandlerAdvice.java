package com.example.productservice.advices;

import com.example.productservice.dtos.ArithmeticExceptionDto;
import com.example.productservice.dtos.ArrayIndexOutOfBoundsExceptionDto;
import com.example.productservice.dtos.ExceptionDto;
import com.example.productservice.exceptions.InvalidProductIdException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandlerAdvice {
    @ExceptionHandler(ArithmeticException.class)
    public ResponseEntity<ArithmeticExceptionDto> handleArithmeticException() {
        ArithmeticExceptionDto arithmeticExceptionDto = new ArithmeticExceptionDto();
        arithmeticExceptionDto.setMessage("Something went wrong");
        arithmeticExceptionDto.setDetail(" right now");
        return new ResponseEntity<>(arithmeticExceptionDto, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ArrayIndexOutOfBoundsException.class)
    public ResponseEntity<ArrayIndexOutOfBoundsExceptionDto> handleAArrayIndexOutOfBoundsException() {
        return null;
    }

    @ExceptionHandler(InvalidProductIdException.class)
    public ResponseEntity<ExceptionDto> InvalidProductIdException(InvalidProductIdException exception) {
        ExceptionDto exceptionDto = new ExceptionDto();
        exceptionDto.setProductId(exception.getProductId());
        exceptionDto.setMessage("Invalid productid passed, please retry with a valid productid");
        return new ResponseEntity<>(exceptionDto, HttpStatus.BAD_REQUEST);
    }
}
