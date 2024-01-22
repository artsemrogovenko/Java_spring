package com.example.Task1.services;

import com.example.Task1.model.Task;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class TaskService {
    private final List<Task> taskList = new ArrayList<>();

    public List<Task> getAllTasks() {
        return taskList;
    }

    public Task getTask(UUID uuid) {
        return taskList.stream().filter(task -> task.getId().equals(uuid)).findFirst().orElse(null);
    }

    public Task addTask(Task task) {
        taskList.add(task);
        return task;
    }

    public void deleteTask(UUID uuid) {
        taskList.removeIf(task -> task.getId().equals(uuid));
    }

    public void updateTask( Task newtask) {
        Task temp = getTask(newtask.getId());
        if (temp != null) {
            temp.setDescription(newtask.getDescription());
            temp.setStatus(newtask.getStatus());
            temp.setName(newtask.getName());
        }
    }
        //return temp;
//    }
//    public void updateTask(UUID uuid, Task newtask) {
//        Task temp = getTask(uuid);
//        if (temp != null) {
//            temp.setDescription(newtask.getDescription());
//            temp.setStatus(newtask.getStatus());
//            temp.setName(newtask.getName());
//        }
//        //return temp;
//    }

    public void setComplete(UUID uuid){
        getTask(uuid).setComplete();
    }

}
