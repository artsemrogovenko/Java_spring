package com.example.webclient.models;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Объект с товаром.
 */
@Data
@NoArgsConstructor
public class Task {
    private Long id;
    private String description;
    private TaskStatus status;
    private String createdTime;
    private Long owner;
    private boolean reserved;

    public Task(String description) {
        this.description = description;
    }
}
