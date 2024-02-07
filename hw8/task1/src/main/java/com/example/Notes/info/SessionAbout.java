package com.example.Notes.info;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * Класс для отображения информации о пользователе в логе
 */
@Data
@Component
@NoArgsConstructor
public class SessionAbout {
    private String id;
    private String userAgent;

}