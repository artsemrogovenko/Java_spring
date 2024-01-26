package com.example.Task1.controllers;

import com.example.Task1.model.Task;
import com.example.Task1.model.TaskStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import com.example.Task1.services.TaskService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class JsonController {
    private final TaskService taskService;

    @PostMapping
    public Task addTask(@RequestBody Task task) {
        return taskService.addTask(task);
    }

    @GetMapping
    public List<Task> getAllTasks() {
        return taskService.getAllTasks();
    }

    @GetMapping("/status/{status}")
    public List<Task> getTasksByStatus(@PathVariable TaskStatus status) {
        return taskService.getByStatus(status);
    }

    @PutMapping("/{id}")
    public Task updateTaskStatus(@PathVariable Long id, @RequestBody Task task) {
        return taskService.updateTaskStatus(id, task);
    }

    @DeleteMapping("/{id}")
    public void deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
    }


}
