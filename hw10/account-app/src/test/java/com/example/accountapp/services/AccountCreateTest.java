package com.example.accountapp.services;

import com.example.accountapp.models.Account;
import com.example.accountapp.repositories.AccountRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
class AccountCreateTest {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private AccountCreate accountCreate;

    @Test
    @Transactional
    void init() {
        List<Account> accounts = accountRepository.findAll();
        System.out.println(accounts);

        assertThat(accounts).hasSize(3).isNotEqualTo("");
    }

    @AfterEach
    void tearDown() {
        accountRepository.deleteAll(); // Здесь вызывайте методы для очистки базы данных
    }
}