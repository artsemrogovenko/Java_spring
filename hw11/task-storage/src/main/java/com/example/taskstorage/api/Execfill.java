package com.example.taskstorage.api;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Execfill {
    @Autowired
    private FillRepo fillRepo;

    @PostConstruct
    private void init() {
        fillRepo.initializeData();
    }
}
