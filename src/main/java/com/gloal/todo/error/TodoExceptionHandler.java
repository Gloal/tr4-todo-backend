package com.gloal.todo.error;

import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class TodoExceptionHandler {

    @ExceptionHandler(value={TodoNotFoundException.class})
    public ResponseEntity<ApiErrorResponse> handleException(TodoNotFoundException e, ServletServerHttpRequest request){
        ApiErrorResponse apiErrorResponse = new ApiErrorResponse(
                String.valueOf(request.getURI()),
                e.getMessage(),
                HttpStatus.NOT_FOUND.value(),
                LocalDateTime.now()
        );

        return new ResponseEntity<>(apiErrorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value={Exception.class})
    public ResponseEntity<ApiErrorResponse> handleException(Exception e, ServletServerHttpRequest request){
        ApiErrorResponse apiErrorResponse = new ApiErrorResponse(
                String.valueOf(request.getURI()),
                e.getMessage(),
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                LocalDateTime.now()
        );

        return new ResponseEntity<>(apiErrorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
