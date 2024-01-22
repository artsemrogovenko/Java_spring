package com.example.Task1.model;

import javax.xml.crypto.Data;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

public class Task {

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
        this.completionTime=null;
    }
public void statusonlist(String statusList) {
        this.status = Status.valueOf(statusList);
    }

    public String getCompletionTime() {
        return this.completionTime;
    }

    public void setCompletionTime() {
        this.completionTime = LocalDateTime.now().format( DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));
    }

    public void setComplete() {
        if (this.status != Status.DONE) {
            this.status = Status.DONE;
            setCompletionTime();
        }
    }

    public enum Status {
        TO_DO,
        IN_PROGRESS,
        DONE
    }

    private UUID id;
    private String name;
    private String description;
    private Status status;
    private String completionTime;

    public Task(String name, String description) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.description = description;
        this.status = Status.TO_DO;
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", status=" + status +
                ", completionTime='" + completionTime + '\'' +
                '}';
    }
}
