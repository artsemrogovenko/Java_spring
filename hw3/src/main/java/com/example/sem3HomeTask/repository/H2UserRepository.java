package com.example.sem3HomeTask.repository;

import com.example.sem3HomeTask.domain.User;
import com.example.sem3HomeTask.services.NotificationService;
import com.example.sem3HomeTask.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.util.List;

@Repository
@Profile("prod")
public class H2UserRepository implements UserRepository {
    @Autowired
    NotificationService notificationService;

    private final JdbcTemplate jdbc;

    public H2UserRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public List<User> getUsers() {
        String sql = "SELECT * FROM userTable";

        RowMapper<User> userRowMapper = (r, i) -> {
            User rowObject = new User();
            rowObject.setName(r.getString("name"));
            rowObject.setAge(r.getInt("age"));
            rowObject.setEmail(r.getString("email"));
            return rowObject;
        };

        return jdbc.query(sql, userRowMapper);
    }

    @Override
    public void addUser(User user) {
        String sql = "INSERT INTO userTable VALUES (?, ?, ?)";
        jdbc.update(sql, user.getName(), user.getAge(), user.getEmail());
        notificationService.notifyUser(user);
    }
}
