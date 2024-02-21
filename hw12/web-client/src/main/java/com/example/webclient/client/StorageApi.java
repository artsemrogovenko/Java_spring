package com.example.webclient.client;

import com.example.webclient.aspect.LogMethod;
import com.example.webclient.models.Task;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@FeignClient(name = "storage")
public interface StorageApi {
@LogMethod
    @GetMapping
    List<Task> getTasks();
    @LogMethod
    @GetMapping("{id}")
    ResponseEntity<Task> getTask(@PathVariable Long id);

    @LogMethod
    @PostMapping("/create")
    ResponseEntity<?> createTask(@RequestBody Task task);
    @LogMethod
    @PostMapping("/{id}/reserve")
    ResponseEntity<?> reserveTask(@PathVariable Long id, @RequestBody Long userId);
    @LogMethod
    @PostMapping("/{id}/complete")
    void completeTask(@PathVariable Long id, @RequestBody Long userId);
    @LogMethod
    @PostMapping("/{id}/reserve/rollback")
    void rollbackReserve(@PathVariable Long id);


}
