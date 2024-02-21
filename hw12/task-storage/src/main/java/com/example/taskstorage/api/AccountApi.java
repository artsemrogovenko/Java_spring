package com.example.taskstorage.api;

import com.example.taskstorage.models.Transaction;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import com.example.taskstorage.models.Task;

import java.util.List;

@FeignClient(name = "transfer")
public interface AccountApi {
    @PostMapping()
    ResponseEntity<?> assign(@RequestBody Transaction transaction);

    @PostMapping("/rollback")
    void rollbackAssign(@RequestBody Transaction transaction);

    @GetMapping("/sync")
    ResponseEntity<List<Task>> syncTasks();

    @GetMapping("/getByDescription/{id}")
    ResponseEntity<Task> getTaskByDescr(@PathVariable Long id, @RequestBody  String descr);


}
