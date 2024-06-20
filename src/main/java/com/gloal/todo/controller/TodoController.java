package com.gloal.todo.controller;

import com.gloal.todo.service.TodoService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import com.gloal.todo.model.Todo;

import java.util.List;


@RestController
@Validated
@RequestMapping("/v1/todos")
public class TodoController {

    private static final Logger LOGGER = LoggerFactory.getLogger(TodoController.class);

    @Autowired
    TodoService todoService;

    @GetMapping
    public ResponseEntity<List<Todo>> fetchAllTodos() {
        LOGGER.info("Get request /v1/todos path");
        List<Todo> todoList = todoService.fetchAllTodos();
        return ResponseEntity.ok(todoList);
    }

    @GetMapping("/{todoId}")
    public ResponseEntity<Todo> fetchTodoById(@PathVariable("todoId") @Min(1) Long todoId) {
        LOGGER.info("Get request /v1/todos/{} path", todoId);
        Todo todo = todoService.fetchTodoById(todoId);
        return ResponseEntity.ok(todo);
    }

    @PostMapping
    public ResponseEntity<Todo> saveTodo(@Valid @RequestBody Todo todo) {
        LOGGER.info("Post request /v1/todos");
        Todo savedTodo = todoService.saveTodo(todo);
        return ResponseEntity.ok(savedTodo);
    }

    @PutMapping("/{todoId}")
    public ResponseEntity<Todo> updateTodoById(@PathVariable("todoId") @Min(1) Long todoId, @Valid @RequestBody Todo updatedTodo) {
        LOGGER.info("Put request /v1/todos/{}", todoId);
        Todo todo = todoService.updateTodoById(updatedTodo, todoId);
        return ResponseEntity.ok(todo);
    }

    @DeleteMapping("/{todoId}")
    public ResponseEntity<Void> deleteTodoById(@PathVariable("todoId") @Min(1) Long todoId) {
        LOGGER.info("Delete request /v1/todos/{}", todoId);
        todoService.deleteTodoById(todoId);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteAllTodos() {
        LOGGER.info("Delete request /v1/todos");
        todoService.deleteAllTodos();
        return ResponseEntity.noContent().build();
    }
}

