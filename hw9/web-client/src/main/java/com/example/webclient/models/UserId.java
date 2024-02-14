package com.example.webclient.models;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Id пользователя
 */
@Data
@NoArgsConstructor
public class UserId {
    Long value;
    public UserId(Long value) {
        this.value = value;
    }
}
