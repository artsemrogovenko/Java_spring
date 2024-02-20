package com.example.accountapp.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

/**
 * Сущность задачи.
 */
@Data
@Entity
@Table
@NoArgsConstructor
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;
    private TaskStatus status;

    private String createdTime;
    private Long owner;
    private boolean reserved;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id")
    private Account account;  // Ссылка на аккаунт, к которому принадлежит задача

}
