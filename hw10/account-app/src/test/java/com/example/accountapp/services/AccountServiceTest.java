package com.example.accountapp.services;

import com.example.accountapp.models.Account;
import com.example.accountapp.models.Task;
import com.example.accountapp.models.Transaction;
import com.example.accountapp.repositories.AccountRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class AccountServiceTest {
    @Autowired
    private AccountCreate accountCreate;

    @Autowired
    private AccountRepository accountRepository;

    private AccountService accountService;

    private static List<Task> taskList = new ArrayList<>();
    private static List<Transaction> transactions = new ArrayList<>();

    @AfterEach
    void tearDown() {
        accountRepository.deleteAll();
    }

    @BeforeEach
    @Transactional
    void setUp() {
        accountService = new AccountService(accountRepository);
        for (int i = 1; i < 11; i++) {
            Task temp = new Task();
            temp.setDescription("task_" + i);
            taskList.add(temp);
        }
        int i = 1;
        for (Task task : taskList) {

            if (i == 3) {
                i = 1;
            }

            Transaction transaction = new Transaction();
            transaction.setDestination(Long.valueOf(i));
            transaction.setSender(2L);
            transaction.setTask(task);

            transactions.add(transaction);
            i++;
        }
        sendTransactions();
    }

    private void sendTransactions() {
        for (Transaction transaction : transactions) {
            accountService.transaction(transaction);
        }
    }

    @Test
    @Transactional
    void getTaskByOwner() {
        List<Account> accounts = accountService.getAllAccounts();
        System.out.println(accounts);

        ResponseEntity<Task> taskresponse = (ResponseEntity<Task>) accountService.getTaskByOwner(1L, "task_1");
        Task taskVerify = taskresponse.getBody();

        assertThat(taskVerify.getDescription()).isEqualTo("task_1");
        assertThat(taskVerify).isNotEqualTo(taskList.get(2));
    }

    @Test
    @Transactional
    void transactionTest() {
        ResponseEntity<?> response = accountService.transaction(transactions.get(2));
        System.out.println(response);

        assertThat(true).isEqualTo(response.getStatusCode().is2xxSuccessful());
    }


    @Test
    @Transactional
    void getAllTasks() {
        List<Task> list = accountService.getAllTasks();
        System.out.println(list.stream().sorted((o1, o2) -> Math.toIntExact(o1.getId() - o2.getId())).collect(Collectors.toList()));

        assertThat(list).hasSize(10).contains(taskList.get(3));
    }
}