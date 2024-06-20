package com.gloal.todo.controller;

import com.gloal.todo.error.TodoNotFoundException;
import com.gloal.todo.model.Todo;
import com.gloal.todo.service.TodoService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest
@AutoConfigureMockMvc
public class TodoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    TodoService todoService;
    private Todo todo;

    @BeforeEach
    void setUp() {
        todo = Todo.builder()
                .task("Book Meeting")
                .isCompleted(false)
                .build();
    }
    @Test
    void shouldReturnAllTodos() throws Exception {
        when(todoService.fetchAllTodos()).thenReturn(List.of());

        mockMvc.perform(get("/v1/todos"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", Matchers.hasSize(0)));
    }

    @Test
    void shouldReturnTodoWhenValidId() throws Exception {
        when(todoService.fetchTodoById(anyLong())).thenReturn(todo);

        mockMvc.perform(get("/v1/todos/18"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.task").value(todo.getTask()));
    }

    @Test
    void shouldReturnTodoNotFoundExceptionWhenInvalidId() throws Exception {
        when(todoService.fetchTodoById(anyLong())).thenThrow(new TodoNotFoundException("Todo with id: 99 not found"));

        mockMvc.perform(get("/v1/todos/99"))
                .andExpect(status().isNotFound());
    }

    @Test
    void shouldReturnBadRequestWhenInvalidIdType() throws Exception {
        mockMvc.perform(get("/v1/todos/hi"))
                .andExpect(status().isBadRequest());
    }
}
