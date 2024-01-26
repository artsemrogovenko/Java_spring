package com.example.Task1.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


@Data
@Entity
@Table(name = "tasks_table")
@NoArgsConstructor
public class Task {

    public void statusonlist(String statusList) {
        this.status = TaskStatus.valueOf(statusList);
    }


    public String currentTime() {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));
    }

    public void setComplete() {
        if (this.status != TaskStatus.DONE) {
            this.status = TaskStatus.DONE;
        }
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "description" , columnDefinition = "VARCHAR(255) NOT NULL")
    private String description;
    @Enumerated(value = EnumType.STRING)
    @Column(name = "status", columnDefinition = "VARCHAR(50) NOT NULL DEFAULT 'TO_DO'")
    private TaskStatus status;
    @CreationTimestamp
    @Column(name = "created", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime created;

    @PrePersist
    protected void onCreate() {
        if (this.status == null) {
            this.status = TaskStatus.TO_DO;
        }
    }
    public Task(String description){
        this.description=description;
    }
    public Task(String description,TaskStatus status){
        this.description=description;
        this.status=status;
    }

}
