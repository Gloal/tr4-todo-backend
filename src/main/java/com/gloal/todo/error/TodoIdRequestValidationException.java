package com.gloal.todo.error;

public class TodoIdRequestValidationException extends RuntimeException{
    public TodoIdRequestValidationException(String message){
        super(message);
    }
}
