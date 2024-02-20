package com.example.accountapp.controllers;

import com.example.accountapp.models.Account;
import com.example.accountapp.models.Task;
import com.example.accountapp.models.Transaction;
import com.example.accountapp.services.AccountService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@AllArgsConstructor
public class AccountController {

    private final AccountService accountService;
    @GetMapping("/getByDescription/{id}")
    public ResponseEntity<?> getTask(@PathVariable Long ownerId, @RequestBody  String descr){
       return accountService.getTaskByOwner(ownerId,descr);
    }
    @GetMapping()
    public ResponseEntity<List<Account>> getAccounts() {
        return ResponseEntity.ok().body(accountService.getAllAccounts());
    }

    @GetMapping("/sync")
    public ResponseEntity<List<Task>> getAllTasks() {
        return ResponseEntity.ok().body(accountService.getAllTasks());
    }

    @PostMapping()
    public ResponseEntity<?> transaction(@RequestBody Transaction transaction) {
        return accountService.transaction(transaction);
    }

    @PostMapping("/rollback")
    public ResponseEntity<Void> rollbackTransaction(@RequestBody Transaction transaction) {
        accountService.rollbackTransaction(transaction);
        System.out.println(transaction);
        return ResponseEntity.ok().body(null);
    }
}
