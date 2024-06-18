package com.gloal.todo.service;

import com.gloal.todo.error.TodoNotFoundException;
import com.gloal.todo.model.Todo;
import com.gloal.todo.repository.TodoRepository;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class TodoServiceImpl implements TodoService{

    TodoRepository todoRepository;

    @Override
    public List<Todo> fetchAllTodos() {
        return todoRepository.findAll();
    }

    @Override
    public Todo fetchTodoById(Long todoId) throws TodoNotFoundException {
        Optional<Todo> todo = todoRepository.findById(todoId);

        if(todo.isEmpty()){
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

        if(Objects.nonNull(updatedTodo.getTask()) &&
                !updatedTodo.getTask().isEmpty()){
            oldTodo.setTask(updatedTodo.getTask());
        }

        if(Objects.nonNull(updatedTodo.getCompleted()) &&
                !updatedTodo.getCompleted().equals(oldTodo.getCompleted())){
            oldTodo.setCompleted(updatedTodo.getCompleted());
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
}
