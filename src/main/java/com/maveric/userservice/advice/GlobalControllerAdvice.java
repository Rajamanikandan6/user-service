package com.maveric.userservice.advice;

import com.maveric.userservice.dto.Error;
import com.maveric.userservice.exception.EmailDuplicationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.maveric.userservice.exception.UserNotFoundException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.servlet.NoHandlerFoundException;

@RestControllerAdvice
public class GlobalControllerAdvice {
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Error> internalServerError(Exception exception){
        Error error = getError(String.valueOf(exception.getMessage()),String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR));
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Error>  handleNullInput(MethodArgumentNotValidException methodArgumentNotValidException){
        Error error = getError(methodArgumentNotValidException.getMessage(),String.valueOf(HttpStatus.BAD_REQUEST));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Error>  handleNullInput(UserNotFoundException userNotFoundException){
        Error error = getError(userNotFoundException.getMessage(),String.valueOf(HttpStatus.NOT_FOUND.value()));
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(EmailDuplicationException.class)
    public ResponseEntity<Error>  handleNullInput(EmailDuplicationException e){
        Error error = getError(e.getMessage(),String.valueOf(HttpStatus.ALREADY_REPORTED.value()));
        return ResponseEntity.status(HttpStatus.ALREADY_REPORTED).body(error);
    }

    @ExceptionHandler(HttpServerErrorException.ServiceUnavailable.class)
    public ResponseEntity<Error>  serviceUnavailable(HttpServerErrorException.ServiceUnavailable serviceUnavailable){
        Error error = getError(String.valueOf(serviceUnavailable.getMessage()),String.valueOf(HttpStatus.SERVICE_UNAVAILABLE));
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(error);
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<Error>  noHandlerException(NoHandlerFoundException noHandlerFoundException){
        Error error = getError(String.valueOf(noHandlerFoundException.getMessage()),String.valueOf(HttpStatus.NOT_FOUND));
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    private Error getError(String message , String code){
        Error error = new Error();
        error.setCode(code);
        error.setMessage(message);
        return error;

    }
}
