package com.example.accountapp.services;

import com.example.accountapp.models.Account;
import com.example.accountapp.repositories.AccountRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AccountCreate {
    @Autowired
    AccountRepository accountRepository;
    @PostConstruct
    public void initializeAccount() {
        Account server = new Account("desk");
        Account admin = new Account("admin");
        Account user = new Account("user");

        accountRepository.saveAll(List.of(server,admin,user));
    }

    public void createAccount(String name){
        accountRepository.save( new Account(name));
    }

}
