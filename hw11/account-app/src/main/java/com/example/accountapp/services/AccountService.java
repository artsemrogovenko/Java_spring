package com.example.accountapp.services;

import com.example.accountapp.models.Task;
import com.example.accountapp.models.Transaction;
import com.example.accountapp.models.exceptions.DuplicateExeption;
import com.example.accountapp.repositories.AccountRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.accountapp.models.Account;

import java.time.LocalTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.Metrics;

@Service
public class AccountService {

    private final Counter transactionCounter = Metrics.counter("transaction_Counter");
    private final AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public ResponseEntity<?> getTaskByOwner(Long userId, String description) {
        Optional<Task> taskOptional = accountRepository.getReferenceById(userId).getTasks().stream()
                .filter(task -> task.getDescription().equals(description)).findFirst();

        System.out.println(taskOptional);

        if (taskOptional.isPresent()) {
            Task task = taskOptional.get();
            return ResponseEntity.ok(task); // Вернуть найденный объект Task
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Задача не найдена");
        }
    }

    /**
     * Проведение обмена между пользователями.
     *
     * @param transaction объект с данными для транзакции.
     */
    @Transactional
    public ResponseEntity<?> transaction(Transaction transaction) {
        transactionCounter.increment();
        try {
            Task newTask = transaction.getTask();
            Account recieverAccount = accountRepository.findById(transaction.getDestination()).get();
            Account senderAccount = accountRepository.findById(transaction.getSender()).get();
            if (recieverAccount.getTasks().stream() // очень сложная проверка)
                    .anyMatch(task -> task.getDescription().equals(newTask.getDescription())
                            && task.getOwner().equals(transaction.getDestination())
                            && task.getStatus() == newTask.getStatus()
                            && task.isReserved())) {
                System.out.println("блок проверки");
                throw new DuplicateExeption("такая задача в корзине есть");
            } else {

                newTask.setOwner(recieverAccount.getId());

//                senderAccount.setTasks(senderAccount.getTasks().stream().filter(task -> !task.equals(newTask)).collect(Collectors.toSet()));
                senderAccount.getTasks().remove(newTask);
                recieverAccount.getTasks().add(newTask);

                accountRepository.save(senderAccount);
                accountRepository.save(recieverAccount);
                // Если ошибок нет, возвращаем успешный ответ
                System.out.println("сохранил изменения " + LocalTime.now());
                return ResponseEntity.ok().build();
            }

        } catch (DuplicateExeption e) {
            System.out.println("перехват ошибки");
            // Возвращаем информацию об ошибке в теле ответа
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Ошибка: " + e.getMessage());
        }
    }


    /**
     * Откат произведенной транзакции.
     *
     * @param transaction объект с данными для транзакции.
     */
    @Transactional
    public void rollbackTransaction(Transaction transaction) {
        System.out.println(transaction);
        Task newTask = transaction.getTask();
        Account recieverAccount = accountRepository.findById(transaction.getDestination()).get();
        Account senderAccount = accountRepository.findById(transaction.getSender()).get();

        newTask.setOwner(recieverAccount.getId());

        senderAccount.getTasks().remove(newTask);
        recieverAccount.getTasks().add(newTask);

        accountRepository.save(senderAccount);
        accountRepository.save(recieverAccount);
    }

    public List<Account> getAllAccounts() {
        return accountRepository.findAll();
    }

    /**
     * Получение всех задач от владельцев.
     */
    public List<Task> getAllTasks() {
        return getAllAccounts().stream().flatMap(account -> account.getTasks().stream()).filter(Objects::nonNull).collect(Collectors.toList());
    }
}
