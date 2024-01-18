package com.example.sem3HomeTask.repository;

import com.example.sem3HomeTask.domain.User;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Profile("test")
public class ListUserRepository implements UserRepository{
    @Override
    public List<User> getUsers() {
        return users;
    }

    @Override
    public void addUser(User user) {
       this.users.add(user);
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    private List<User> users = new ArrayList<>();

}
