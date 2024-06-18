package com.gloal.todo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
@Entity
public class Todo {
    @Id
    private Long taskId;
    private String task;
    private Boolean completed;
}
