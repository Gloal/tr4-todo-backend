package com.gloal.todo.service;

import com.gloal.todo.controller.TodoController;
import com.gloal.todo.error.TodoNotFoundException;
import com.gloal.todo.model.Todo;
import com.gloal.todo.repository.TodoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class TodoServiceImpl implements TodoService {
    private static final Logger LOGGER = LoggerFactory.getLogger(TodoController.class);
    @Autowired
    TodoRepository todoRepository;

    @Override
    public List<Todo> fetchAllTodos() {
        try {
            return todoRepository.findAll();
        }catch (Exception e){
           LOGGER.error("Error retrieving todo list", e);
        }
        return new ArrayList<>();
    }

    @Override
    public Todo fetchTodoById(Long todoId) throws TodoNotFoundException {
        Optional<Todo> todo = todoRepository.findById(todoId);

        if (todo.isEmpty()) {
            throw new TodoNotFoundException("Todo not found");
        }
        return todo.get();
    }

    @Override
    public Todo saveTodo(Todo todo) {
        return todoRepository.save(todo);
    }

    @Override
    public Todo updateTodoById(Todo updatedTodo, Long todoId) {
        Todo oldTodo = todoRepository.findById(todoId).get();

        if (Objects.nonNull(updatedTodo.getTask()) &&
                !updatedTodo.getTask().isEmpty()) {
            oldTodo.setTask(updatedTodo.getTask());
        }

        if (Objects.nonNull(updatedTodo.getIsCompleted()) &&
                !updatedTodo.getIsCompleted().equals(oldTodo.getIsCompleted())) {
            oldTodo.setIsCompleted(updatedTodo.getIsCompleted());
        }

        return todoRepository.save(oldTodo);
    }

    @Override
    public void deleteTodoById(Long todoId) {
        todoRepository.deleteById(todoId);
    }

    @Override
    public void deleteAllTodos() {
        todoRepository.deleteAll();

    }

    public Long getNumberOfIncompleteTasks(){
        return todoRepository.countByIsCompleted(true);
    }


}
