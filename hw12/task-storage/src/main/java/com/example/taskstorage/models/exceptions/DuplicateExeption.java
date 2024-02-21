package com.example.taskstorage.models.exceptions;

public class DuplicateExeption extends IllegalStateException {
    public DuplicateExeption(String message) {
        super(message);
    }
}
