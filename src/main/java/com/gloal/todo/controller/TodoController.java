package com.gloal.todo.controller;

import com.gloal.todo.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.gloal.todo.model.Todo;

import java.util.List;


@RestController
public class TodoController {

    @Autowired
    TodoService todoService;

    @GetMapping("/v1/todos")
    public ResponseEntity<List<Todo>> fetchAllTodos() {
        List<Todo> todoList = todoService.fetchAllTodos();
        return ResponseEntity.ok(todoList);
    }

}
