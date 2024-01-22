package com.example.demo.repositories;

import com.example.demo.model.Queries;
import com.example.demo.model.User;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@AllArgsConstructor
public class UserRepository {

    private final JdbcTemplate jdbc;
    private final Queries queries;
//    public UserRepository(JdbcTemplate jdbc) {
//        this.jdbc = jdbc;
//    }

    public List<User> findAll() {
        String sql = queries.getSelectAllUsers();

        RowMapper<User> userRowMapper = (r, i) -> {
            User rowObject = new User();
            rowObject.setId(r.getInt("id"));
            rowObject.setFirstName(r.getString("firstName"));
            rowObject.setLastName(r.getString("lastName"));
            return rowObject;
        };

        return jdbc.query(sql, userRowMapper);
    }

    public User save(User user) {
        String sql = queries.getInsertUser();
        jdbc.update(sql, user.getFirstName(), user.getLastName());
        return user;
    }

    public void deleteById(int id) {
        String sql = queries.getDeleteUser() + id;
        jdbc.execute(sql);
        System.out.println("Deleted Record with ID = " + id);
    }

    public User findById(int id) {
        User obj = null;
        for (User u:  findAll()){
            if (u.getId()==id){
                obj=u;
                break;
            }
        }
        return obj;
    }


    public void update(User user) {
        if (user!=null){
            String sql = queries.getUpdateUser();
            jdbc.update(sql, user.getFirstName(), user.getLastName(), user.getId());
            System.out.println("Updated ID = " + user.getId());
        }
    }
}
