package com.maveric.userservice.advice;

import com.maveric.userservice.constant.ErrorMessageConstants;
import com.maveric.userservice.dto.Error;
import com.maveric.userservice.exception.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpServerErrorException;

@RestControllerAdvice
public class GlobalControllerAdvice {
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Error> internalServerError(Exception exception){
        Error error = getError(String.valueOf(exception.getMessage()),String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR));
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Error>  handleNullInput(MethodArgumentNotValidException methodArgumentNotValidException){
        Error error = getError(ErrorMessageConstants.MISSING_INPUT,String.valueOf(HttpStatus.BAD_REQUEST));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Error>  handleNullInput(UserNotFoundException userNotFoundException){
        Error error = getError(userNotFoundException.getMessage(),String.valueOf(HttpStatus.NOT_FOUND.value()));
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(HttpServerErrorException.ServiceUnavailable.class)
    public ResponseEntity<Error>  serviceUnavailable(HttpServerErrorException.ServiceUnavailable serviceUnavailable){
        Error error = getError(String.valueOf(serviceUnavailable.getMessage()),String.valueOf(HttpStatus.SERVICE_UNAVAILABLE));
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(error);
    }

    private Error getError(String message , String code){
        Error error = new Error();
        error.setCode(code);
        error.setMessage(message);
        return error;

    }
}
