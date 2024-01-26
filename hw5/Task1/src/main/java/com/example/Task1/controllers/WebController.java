package com.example.Task1.controllers;

import com.example.Task1.model.Task;
import com.example.Task1.model.TaskStatus;
import com.example.Task1.repository.TaskRepository;
import com.example.Task1.services.TaskService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.openwms.core.integration.exception.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Optional;


@Controller
@RequiredArgsConstructor
@ControllerAdvice
public class WebController {
    private final TaskService taskService;
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?>
    handleResourceNotFoundException(ResourceNotFoundException exception) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(exception.getMessage());
    }
    @PostMapping("/task-add")
    public String htmladdTask(Task newtask) {
        if (!newtask.getDescription().isEmpty()) {
            taskService.addTask(newtask);
        }
        return "redirect:/web";
    }

    @GetMapping("/web")
    public String htmlgetAllTasks(Model model) {
        List<Task> list = taskService.getAllTasks();
        model.addAttribute("tasks", list);
        model.addAttribute("task",new Task());
        return "index";
    }

    @PostMapping("/filterstatus/{status}")
    public String htmlgetByStatus(@PathVariable TaskStatus status, Model model) {
        System.out.println(status);
        model.addAttribute("tasks", taskService.getByStatus(status));
        return "index.html";
    }

    @PostMapping("/task-edit/{id}")
    public String htmlupdateTaskStatus(@PathVariable Long id, @ModelAttribute Task task, Model model) {
        System.out.println(id);
        System.out.println(task);
        taskService.updateTaskStatus(id, task);
        model.addAttribute("tasks", taskService.getAllTasks());
        return "redirect:/web";
    }

    @PostMapping("/task-complete/{id}")
    public String completeTask(@PathVariable Long id, Model model) {
        taskService.setComplete(id);
        model.addAttribute("tasks", taskService.getAllTasks());
        return "redirect:/web";
    }

    @PostMapping("/task-delete/{id}")
    public String htmldeleteTask(@PathVariable Long id, Model model) {
        taskService.deleteTask(id);
        model.addAttribute("tasks", taskService.getAllTasks());
        return "redirect:/web";
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
