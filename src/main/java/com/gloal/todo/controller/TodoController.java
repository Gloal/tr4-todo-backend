package com.gloal.todo.controller;

import com.gloal.todo.service.TodoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import com.gloal.todo.model.Todo;

import java.util.List;


@RestController
public class TodoController {

    private static final Logger LOGGER = LoggerFactory.getLogger(TodoController.class);

    @Autowired
    TodoService todoService;

    @GetMapping("/v1/todos")
    public ResponseEntity<List<Todo>> fetchAllTodos() {
        LOGGER.info("Get request /v1/todos path");
        List<Todo> todoList = todoService.fetchAllTodos();
        return ResponseEntity.ok(todoList);
    }

    @GetMapping("/v1/todos/{id}")
    public ResponseEntity<?> fetchTodoById(@PathVariable("todoId") Long todoId ){
        LOGGER.info("Get request /v1/todos/{} path", todoId);
        try {
            return ResponseEntity.ok(todoService.fetchTodoById(todoId));
        } catch (Exception e){
            return new ResponseEntity<String>(todoId + " not found", HttpStatus.NOT_FOUND);
        }



    }

}
