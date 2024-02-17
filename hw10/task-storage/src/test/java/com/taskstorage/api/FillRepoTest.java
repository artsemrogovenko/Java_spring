package com.taskstorage.api;

import com.taskstorage.models.Task;
import com.taskstorage.models.Transaction;
import com.taskstorage.repositories.TaskRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.lang.reflect.Method;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.http.HttpStatus.INSUFFICIENT_STORAGE;

class FillRepoTest {

    FillRepo fillRepo;
    AccountApi mockAccountApi;
    TaskRepository mocktaskRepository;

    @BeforeEach
    void mockAccounts() {
        mockAccountApi = Mockito.mock(AccountApi.class);
        mocktaskRepository = Mockito.mock(TaskRepository.class);
        fillRepo = new FillRepo(mocktaskRepository, mockAccountApi);
    }

    @Test
    void testBindinagTask() {
        Task task1 = new Task();
        task1.setDescription("123");
        Task task2 = new Task();
        task2.setDescription("description");

        // Создание мок-объекта Optional с нужным значением
        Optional<Task> optionalTask = Optional.of(task1);
        // создам условие для успешного выполнения блока
        BDDMockito.given(mocktaskRepository.findByDescription("description")).willReturn(optionalTask);

        fillRepo.createTask(task2);
    }

    @Test
    public void testDuplucateThrow() {
        Task task = new Task();
        task.setDescription("abc");

        BDDMockito.given(mocktaskRepository.findByDescription("abc")).willReturn(Optional.of(task));
        ResponseEntity<?> writetask = fillRepo.createTask(task);
        System.out.println(writetask);

        assertEquals(INSUFFICIENT_STORAGE, writetask.getStatusCode());
    }


    @Test
    void transactiontTest() throws Exception {
        Method bindTaskMethod = FillRepo.class.getDeclaredMethod("bindingTask", Task.class);
        bindTaskMethod.setAccessible(true);
        ////
        Task task = new Task();
        Transaction mockTransaction = new Transaction(1L, 2L, task);

        BDDMockito.given(mockAccountApi.assign(mockTransaction)).willReturn(ResponseEntity.ok().build());
        ResponseEntity<?> responseEntity = (ResponseEntity<?>) bindTaskMethod.invoke(fillRepo, task);

        // правильно ли выставлен получатель и отправитель
        Mockito.verify(mockAccountApi).assign(any(Transaction.class));
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }
}