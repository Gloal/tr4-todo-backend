package com.gloal.todo.service;

import com.gloal.todo.model.Todo;
import com.gloal.todo.repository.TodoRepository;

import java.util.List;

@Service
public class TodoServiceImpl implements TodoService{

    TodoRepository todoRepository;

    @Override
    public List<Todo> fetchAllTodos() {
        return todoRepository.findAll();
    }

    @Override
    public Todo fetchTodoById(Long Id) {
        return null;
    }

    @Override
    public Todo saveTodo(Todo newTodo) {
        return null;
    }

    @Override
    public Todo updateTodoById(Todo todoToUpdate) {
        return null;
    }

    @Override
    public void deleteTodoById(Long todoId) {

    }

    @Override
    public void deleteAllTodos() {

    }
}
