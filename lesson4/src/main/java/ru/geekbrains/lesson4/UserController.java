package ru.geekbrains.lesson4;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserServise userServise;

    @GetMapping
    public String listUsers(Model model) {
        model.addAttribute("users", userServise.getAllUsers());
        return "users";
    }
    @GetMapping("/{id}")//http://localhost:8080/users
    public String getUser(@PathVariable Long id, Model model) {
        model.addAttribute("user",userServise.getUserById(id) );
        return "userprofile";
    }



}
