package com.example.taskstorage.observer;

import com.example.taskstorage.aop.TrackUserAction;
import com.example.taskstorage.models.Task;
import lombok.Data;
import org.springframework.context.ApplicationEvent;

@Data
public class TaskCompleteEvent extends ApplicationEvent {
    private Task task;


    public TaskCompleteEvent(Object source, Task task) {
        super(source);
        this.task = task;
    }
}
