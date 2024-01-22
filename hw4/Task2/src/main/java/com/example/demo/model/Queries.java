package com.example.demo.model;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
@Data
@ConfigurationProperties(prefix = "database-queries")
public class Queries {

    private String selectAllUsers;
    private String insertUser;
    private String deleteUser;
    private String updateUser;

    // Геттеры и сеттеры сгенерированы анотацией @Data

}
