package com.gloal.todo.service;

import com.gloal.todo.error.TodoNotFoundException;
import com.gloal.todo.model.Todo;
import com.gloal.todo.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

public interface TodoService {


    List<Todo> fetchAllTodos();

    Todo fetchTodoById(Long todoId) throws TodoNotFoundException;

    Todo saveTodo(Todo newTodo);

    Todo updateTodoById(Todo todoToUpdate, Long todoId);

    void deleteTodoById(Long todoId);

    void deleteAllTodos();

    Long getNumberOfIncompleteTasks();
}
