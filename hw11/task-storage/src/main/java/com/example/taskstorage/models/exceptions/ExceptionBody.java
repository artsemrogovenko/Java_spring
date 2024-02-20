package com.example.taskstorage.models.exceptions;


import lombok.Data;

import java.time.LocalDateTime;

/**
 * Обертка собственного исключения.
 */
@Data
public class ExceptionBody {

    private String message;
    private LocalDateTime dateTime;
}
