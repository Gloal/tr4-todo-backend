package com.gloal.todo.error;

public class TodoNotFoundException extends Exception {

    public TodoNotFoundException(String message){
        super(message);
    }
}
