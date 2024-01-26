package com.example.Task1.services;

import com.example.Task1.model.MyFormatter;
import com.example.Task1.model.Task;
import com.example.Task1.model.TaskStatus;
import com.example.Task1.repository.TaskRepository;
import lombok.AllArgsConstructor;
import org.openwms.core.integration.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor

public class TaskService {
    private final TaskRepository taskRepository;
    public Task addTask(Task task) {
        return taskRepository.save(task);
    }
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    public List<Task> getByStatus(TaskStatus status) {
        return taskRepository.findByStatus(status);
    }


    public Task updateTaskStatus(Long id, Task task) {
        if (taskRepository.existsById(id)) {
            task.setId(id);
            return taskRepository.save(task);
        } else {
            throw new ResourceNotFoundException("Task not found with id: " + id);
        }
}


    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }

    public void setComplete(Long id){
        taskRepository.setTaskStatusDone(id);
    }
}
