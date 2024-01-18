package com.example.sem3HomeTask.repository;

import com.example.sem3HomeTask.domain.User;

import java.util.List;

/**
 * Интерфейс для переключения между профилями
 */
public interface UserRepository {
     List<User> getUsers();
     void addUser(User user);
}
