package com.example.accountapp.repositories;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import com.example.accountapp.models.Account;

/**
 * Репозиторий для работы с пользователями.
 */
@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

    @Modifying
    @Transactional
    void deleteTaskByTasksDescriptionAndId(String description, Long accountId);
}
