package com.gloal.todo.service;

import com.gloal.todo.model.Todo;
import com.gloal.todo.repository.TodoRepository;
import com.gloal.todo.error.TodoNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class TodoServiceTest {

    @Mock
    private TodoRepository todoRepository;

    @InjectMocks
    private TodoServiceImpl todoService;

    private Todo todo1;
    private Todo todo2;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
        todo1 = Todo.builder()
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
        when(todoRepository.findAll()).thenReturn(todoList);

        List<Todo> returnedList = todoService.fetchAllTodos();

        assertEquals("First Task", returnedList.get(0).getTask());
        assertEquals("Second Task", returnedList.get(1).getTask());
    }

    @Test
    void shouldHandleExceptionWhenRepositoryFailsToFetchAllTodos() {
        when(todoRepository.findAll()).thenThrow(new RuntimeException("Repository failed"));

        List<Todo> returnedList = todoService.fetchAllTodos();

        assertTrue(returnedList.isEmpty());
    }

    @Test
    void fetchTodoById() {
        when(todoRepository.findById(1L)).thenReturn(Optional.of(todo1));

        Todo foundTodo = todoService.fetchTodoById(1L);

        assertEquals("First Task", foundTodo.getTask());
    }

    @Test
    void fetchTodoByIdNotFound() {
        when(todoRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(TodoNotFoundException.class, () -> todoService.fetchTodoById(1L));
    }

    @Test
    void saveTodo() {
        when(todoRepository.save(todo1)).thenReturn(todo1);
        when(todoRepository.countByIsCompleted(false)).thenReturn(5L);

        Todo savedTodo = todoService.saveTodo(todo1);

        assertEquals("First Task", savedTodo.getTask());
        assertFalse(savedTodo.getIsCompleted());
    }

    @Test
    void saveTodoExceedsIncompleteLimit() {
        when(todoRepository.countByIsCompleted(false)).thenReturn(10L);

        assertThrows(IllegalArgumentException.class, () -> todoService.saveTodo(todo1));
    }

    @Test
    void updateTodoById() {
        when(todoRepository.findById(1L)).thenReturn(Optional.of(todo1));
        when(todoRepository.save(todo1)).thenReturn(todo1);

        Todo updatedTodo = Todo.builder().task("Updated Task").isCompleted(true).build();
        Todo result = todoService.updateTodoById(updatedTodo, 1L);

        assertEquals("Updated Task", result.getTask());
        assertTrue(result.getIsCompleted());
    }

    @Test
    void updateTodoByIdNotFound() {
        when(todoRepository.findById(1L)).thenReturn(Optional.empty());

        Todo updatedTodo = Todo.builder().task("Updated Task").isCompleted(true).build();

        assertThrows(TodoNotFoundException.class, () -> todoService.updateTodoById(updatedTodo, 1L));
    }

    @Test
    void deleteTodoById() {
        when(todoRepository.existsById(1L)).thenReturn(true);
        doNothing().when(todoRepository).deleteById(1L);

        todoService.deleteTodoById(1L);

        verify(todoRepository, times(1)).deleteById(1L);
    }

    @Test
    void deleteTodoByIdNotFound() {
        when(todoRepository.existsById(1L)).thenReturn(false);

        assertThrows(TodoNotFoundException.class, () -> todoService.deleteTodoById(1L));
    }

    @Test
    void deleteAllTodos() {
        doNothing().when(todoRepository).deleteAll();

        todoService.deleteAllTodos();

        verify(todoRepository, times(1)).deleteAll();
    }

    @Test
    void getNumberOfIncompleteTasks() {
        when(todoRepository.countByIsCompleted(false)).thenReturn(5L);

        Long incompleteTasksCount = todoService.getNumberOfIncompleteTasks();

        assertEquals(5, incompleteTasksCount);
    }

    @Test
    void shouldNotAllowCreationWhen10IncompleteTasks() {
        when(todoRepository.countByIsCompleted(false)).thenReturn(10L);

        assertThrows(IllegalArgumentException.class, () -> todoService.saveTodo(todo1));
    }

    @Test
    @Disabled("Need to refactor method ")
    void shouldNotAllowUpdatingCompletedToIncompleteWhen10IncompleteTasks() {
        Todo completedTodo = Todo.builder()
                .task("Completed Task")
                .isCompleted(true)
                .build();

        when(todoRepository.findById(1L)).thenReturn(Optional.of(completedTodo));
        when(todoRepository.countByIsCompleted(false)).thenReturn(10L);

        Todo updateToIncomplete = Todo.builder()
                .task("Completed Task")
                .isCompleted(false)
                .build();

        assertThrows(IllegalArgumentException.class, () -> todoService.updateTodoById(updateToIncomplete, 1L));
    }
}