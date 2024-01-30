package com.example.example4sem6_rikky_and_morty_rest.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class UpdateUrl {

    @Value("${R&M.path}")
    private String CHARACTER_API;

    private final Environment environment;

    public UpdateUrl(Environment environment) {
        this.environment = environment;
    }

    public void updateCharacterApi(String newApiPath) {
        // Обновить значение только если оно не null
        if (newApiPath != null) {
            this.CHARACTER_API = newApiPath;
        }
    }

}
