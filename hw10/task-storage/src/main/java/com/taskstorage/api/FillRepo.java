package com.taskstorage.api;

import com.taskstorage.models.Task;
import com.taskstorage.models.Transaction;
import com.taskstorage.models.exceptions.DuplicateExeption;
import com.taskstorage.repositories.TaskRepository;
import feign.FeignException;
import feign.RetryableException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.*;

import java.net.ConnectException;
import java.util.NoSuchElementException;

@Service
public class FillRepo {
    private final Long senderId = 2L;
    private final Long storageId = 1L;

    private final TaskRepository taskRepository;

    private final AccountApi accountApi;

    public FillRepo(TaskRepository taskRepository, AccountApi accountApi) {
        this.taskRepository = taskRepository;
        this.accountApi = accountApi;
    }


    // @PostConstruct
    public void initializeData() {
        // Создаем объекты Task и сохраняем их в репозиторий
//        Task note1 = new Task("Поменять лампочку", senderId);
//        Task note2 = new Task("Подготовить презентацию", senderId);
//        Task note3 = new Task("Закончить проект", senderId);
        try {
            syncronizeTasks();
        } catch (FeignException ce) {
            System.out.println("Не удалось подключится к серверу для синхронизации");
        }
//        createTask(note1);
//        createTask(note2);
//        createTask(note3);
    }


    public ResponseEntity<?> createTask(Task newTask) {
        ResponseEntity<?> response = null;
        Task temp = new Task();
        try {
            if (taskRepository.findByDescription(newTask.getDescription()).get().getDescription().equals(newTask.getDescription()))
                throw new DuplicateExeption("такая задача в списке уже есть");

        } catch (NoSuchElementException t) {

            taskRepository.save(newTask);

            temp = taskRepository.findByDescription(newTask.getDescription()).get();
            System.out.println(temp);
            try {
//                ResponseEntity<?> response = bindingTask(temp);
                response = bindingTask(temp);
                System.out.println(response);
                if (response.getStatusCode().is2xxSuccessful()) {
                    temp.setOwner(storageId);
                    taskRepository.save(temp);
                } else {
                    temp.setOwner(senderId);
                    taskRepository.save(temp);
                    rollbackBinding(temp);
                }
            } catch (RetryableException | ConnectException ce) { //проверка на разрыв соединения
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ce.getMessage());

            } catch (HttpClientErrorException ex) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Ошибка: " + ex.getMessage());

            } catch (FeignException.BadRequest req) { // если у пользователя есть такая задача
                if (req.getMessage().contains("такая задача у пользователя есть")) ;
                taskRepository.delete(temp); // удалю новую и запишу от пользователя

                taskRepository.save(accountApi.getTaskByDescr(storageId, newTask.getDescription()).getBody());

                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Ошибка: " + req.getMessage());
            } catch (FeignException.ServiceUnavailable un) {
                return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body("Ошибка: " + un.getMessage());
            }

        } catch (DuplicateExeption ex) {
            System.out.println("block ex " + ex.getMessage());
            return ResponseEntity.status(HttpStatus.INSUFFICIENT_STORAGE).body("Ошибка: " + ex.getMessage());
        }
        return response;
    }


    private ResponseEntity<?> bindingTask(Task newTask) throws HttpClientErrorException, ConnectException, DuplicateExeption {
        return accountApi.assign(new Transaction(storageId, senderId, newTask));
    }

    private void rollbackBinding(Task task) throws HttpClientErrorException {
        System.out.println(task);
        accountApi.rollbackAssign(new Transaction(senderId, storageId, task));
    }


    private void syncronizeTasks() throws FeignException {
        taskRepository.saveAll(accountApi.syncTasks().getBody());
    }

}
