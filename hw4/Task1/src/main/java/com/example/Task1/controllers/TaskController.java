package com.example.Task1.controllers;

import com.example.Task1.model.Task;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.example.Task1.services.TaskService;

import java.util.UUID;

@Controller
public class TaskController {
    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }


    @GetMapping("/task")
    public String getAllTasks(Model model) {
        model.addAttribute("tasks", taskService.getAllTasks());
        return "index";
    }


    @PostMapping("/task-add")
    public String addTask(Task newtask, Model model) {
        System.out.println(newtask);
        if (!newtask.getName().isEmpty() && !newtask.getDescription().isEmpty()) {
            taskService.addTask(newtask);
        }
        model.addAttribute("tasks", taskService.getAllTasks());
        return "redirect:/task";

    }

    @PostMapping("/task-edit")
    public String editTask(@ModelAttribute Task task, Model model) {
        taskService.updateTask(task);
        model.addAttribute("tasks", taskService.getAllTasks());
        return "redirect:/task";
    }


    @PostMapping("/task-delete/{id}")
    public String deleteById(@PathVariable UUID id, Model model) {
        taskService.deleteTask(id);
        model.addAttribute("tasks", taskService.getAllTasks());
        return "redirect:/task";
    }

    @PostMapping("/task-complete/{id}")
    public String completeTask(@PathVariable UUID id, Model model) {
        taskService.setComplete(id);
        model.addAttribute("tasks", taskService.getAllTasks());
        return "redirect:/task";
    }

    @GetMapping("/scripts.js")
    public String scripts() {
        return "../scripts.js";
    }

    @GetMapping("/styles.css")
    public String styles() {
        return "../styles.css";
    }

}
