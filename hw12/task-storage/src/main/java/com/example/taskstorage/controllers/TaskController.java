package com.example.taskstorage.controllers;

import com.example.taskstorage.aop.TrackUserAction;
import com.example.taskstorage.api.FillRepo;
import com.example.taskstorage.models.Task;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.taskstorage.services.TaskService;

import java.util.List;

/**
 * Контроллер работы с задачами.
 */
@RestController
@AllArgsConstructor
public class TaskController {
    /**
     * Сервис работы с задачами.
     */
    private final TaskService taskService;
    private final FillRepo fillRepo;


    @TrackUserAction
    @PostMapping("/create")
    public ResponseEntity<?> createTask(@RequestBody Task task) {
        fillRepo.createTask(task);

        return ResponseEntity.ok().body(null);
//        return responseEntity;
    }

    /**
     * Получение всех задач.
     *
     * @return ответ со списком задач.
     */

    @TrackUserAction
    @GetMapping
    public ResponseEntity<List<Task>> getTasks() {
        return ResponseEntity.ok().body(taskService.getAllTask());
    }

    /**
     * Получение конкретного задачи.
     *
     * @param id идентификатор задачи.
     * @return ответ с задачей.
     */

    @TrackUserAction
    @GetMapping("{id}")
    public ResponseEntity<Task> getTask(@PathVariable("id") Long id) {
        return ResponseEntity.ok().body(taskService.getTaskById(id));
    }

    /**
     * Списание задачи.
     * @param id идентификатор задачи.
     * @return ответ с подтверждением успешного выполнения.
     */

    /**
     * Резервирование задачи.
     *
     * @param id идентификатор задачи.
     * @return ответ с подтверждением успешного выполнения.
     */

    @TrackUserAction
    @PostMapping("{id}/reserve")
    public ResponseEntity<Void> reserveAmount(@PathVariable("id") Long id, @RequestBody Long userId) {
        taskService.reservedTask(id, userId);
        return ResponseEntity.ok().body(null);
    }

    @TrackUserAction
    @PostMapping("/{id}/complete")
    public ResponseEntity<Void> completeTask(@PathVariable Long id, @RequestBody Long userId) {
        taskService.completedTask(id, userId);
        return ResponseEntity.ok().body(null);
    }

    /**
     * Откат резервации задачи.
     *
     * @param id идентификатор задачи.
     * @return ответ с подтверждением успешного выполнения.
     */

    @TrackUserAction
    @PostMapping("{id}/reserve/rollback")
    public ResponseEntity<Void> rollbackReserveAmount(@PathVariable("id") Long id) {
        System.out.println("Контроллер отмены выполнения");
        taskService.rollbackReservedTask(id);
        return ResponseEntity.ok().body(null);
    }

}
