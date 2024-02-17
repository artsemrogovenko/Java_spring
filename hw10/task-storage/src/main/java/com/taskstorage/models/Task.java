package com.taskstorage.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

/**
 * Сущность таски.
 */
@Data
@Entity
@Table(name = "task")
@NoArgsConstructor
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;
    private TaskStatus status;
    @CreationTimestamp
    @Column(name = "created", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private String createdTime;
    private Long owner;
    private boolean reserved;

    @PrePersist
    protected void onCreate() {
        if (this.status == null) {
            this.status = TaskStatus.TO_DO;
        }
    }

}
