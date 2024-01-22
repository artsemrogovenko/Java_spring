package com.example.demo.controllers;

import com.example.demo.model.User;
import com.example.demo.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Slf4j
@Controller
@AllArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/users")
    public String findAll(Model model) {
        log.info("Обработка запроса POST для /users");
        model.addAttribute("users", userService.findAll());
        return "user-list";
    }

    @GetMapping("/user-create")
    public String createUserForm(User user) {
        log.info("Обработка запроса GET для /user-create");
        return "user-create";
    }

    @PostMapping("/user-create")
    public String createUser(User user) {
        log.info("Обработка запроса POST для /user-create");
        userService.saveUser(user);
        return "redirect:/users";
    }

    @GetMapping("user-delete/{id}")
    public String deleteUser(@PathVariable int id) {
        log.info("Обработка запроса GET для user-delete/{id}");
        userService.deleteById(id);
        return "redirect:/users";
    }

    @GetMapping("user-update/{id}")
    public String updateUserForm(@PathVariable int id, Model model) {
        log.info("Обработка запроса GET для user-update/{id}");
        model.addAttribute("user", userService.findById(id));
        return "user-update";
    }

    @PostMapping("/user-update")
    public String updateUser(User user) {
        log.info("Обработка запроса POST для /user-update");
        userService.updateUser(user);
        return "redirect:/users";
    }
}
