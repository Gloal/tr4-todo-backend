package com.gloal.todo.service;

import com.gloal.todo.model.Todo;
import com.gloal.todo.repository.TodoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class TodoServiceTest {

    @Mock
    private TodoRepository todoRepository;

    @InjectMocks
    private TodoService todoService;

    private Todo todo1;
    private Todo todo2;

    @BeforeEach
    void setup(){
        todo1 =  Todo.builder()
                .task("First Task")
                .isCompleted(false)
                .build();

        todo2 = Todo.builder()
                .task("Second Task")
                .isCompleted(true)
                .build();

    }

    @Test
    void shouldReturnAllTodos() {
        List<Todo> todoList = new ArrayList<>(Arrays.asList(todo1, todo2));
        //When
        when(todoRepository.findAll()).thenReturn(todoList);
        //Act
        List<Todo> returnedList = todoService.fetchAllTodos();
        //Assert
        assertEquals("First Task", returnedList.get(0).getTask());
    }

    @Test
    void shouldHandleExceptionWhenRepositoryFailsToFetchAllTodos() {
        List<Todo> todoList = new ArrayList<>();
        //When
        when(todoRepository.findAll()).thenThrow(new RuntimeException("Repository failed"));

        List<Todo> returnedList = todoService.fetchAllTodos();

        assertTrue(returnedList.isEmpty());
        //assertThrows(RuntimeException.class, ()-> todoService.fetchAllTodos());
    }

    @Test
    void fetchTodoById() {
    }

    @Test
    void saveTodo() {
    }

    @Test
    void updateTodoById() {
    }

    @Test
    void deleteTodoById() {
    }

    @Test
    void deleteAllTodos() {
    }

    @Test
    void testFetchAllTodos() {
    }

    @Test
    void testFetchTodoById() {
    }

    @Test
    void testSaveTodo() {
    }

    @Test
    void testUpdateTodoById() {
    }

    @Test
    void testDeleteTodoById() {
    }

    @Test
    void testDeleteAllTodos() {
    }
}