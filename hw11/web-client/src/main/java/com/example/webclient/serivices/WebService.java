package com.example.webclient.serivices;

import com.example.webclient.aspect.LogMethod;
import com.example.webclient.models.Task;
import com.example.webclient.models.TaskStatus;
import com.example.webclient.client.StorageApi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import com.example.webclient.client.AccountApi;
import com.example.webclient.models.Transaction;

import java.util.List;
import java.util.stream.Collectors;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.Metrics;
/**
 * Сервис для осуществления покупки.
 */
@Service
public class WebService {
    /**
     * Объекты клиента Feigen для запросов записи задач .
     */
    private final AccountApi accountApi;
    private final StorageApi storageApi;
    /**
     * счетчик micrometer
     */
    private final Counter getTaskCounter = Metrics.counter("getOne");

    private final String storageAccount;
    private final String userId = "3"; //TODO id пользователя

    public WebService(AccountApi accountApi, StorageApi storageApi, @Value("${desk.account.id}") String storageAccount) {
        this.accountApi = accountApi;
        this.storageApi = storageApi;
        this.storageAccount = storageAccount;
    }

    /**
     * Получение всех задач для админа.
     */
    @LogMethod
    public List<Task> showAll() {
        return storageApi.getTasks();
    }

    /**
     * Получение всех доступных задач для обычного пользователя.
     */
    @LogMethod
    public List<Task> getAll() {//показать только доступные мне задачи TODO доработать
        return storageApi.getTasks();
    }


    @LogMethod
    public ResponseEntity<Task> getOne(Long id) {
        getTaskCounter.increment();
        return storageApi.getTask(id);
    }

    /**
     * Метод присвоения заачи . На каждом этапе происходит проверка,
     * в случае получения исключения происходит откат выполненных транзакций.
     *
     * @param taskId идентификатор задачи.
     * @param userId номер исполнителя.
     */
    @LogMethod
    public void assign(Long taskId, Long userId, Task task) {
        ResponseEntity<?> response = productReserve(taskId, userId);
        if (response.getStatusCode().is2xxSuccessful()) {
            assignTask(storageApi.getTask(task.getId()).getBody(), userId);
        } else {
            rollbackProductReserve(taskId);
        }
    }

    /**
     * Резервирование
     */
    @LogMethod
    private ResponseEntity<?> productReserve(Long id, Long userId) throws HttpClientErrorException {
        return storageApi.reserveTask(id, userId);
    }

    /**
     * Служебный метод отката резервирования
     */
    @LogMethod
    private void rollbackProductReserve(Long id) throws HttpClientErrorException {
        storageApi.rollbackReserve(id);
    }

    @LogMethod
    private ResponseEntity<?> assignTask(Task task, Long userId) throws HttpClientErrorException {
        return accountApi.take(new Transaction(userId, Long.parseLong(storageAccount), task));
    }

    /**
     * Служебный метод отката произведенной оплаты.
     */
    @LogMethod
    public void rollbackTake(Task task, Long userId) throws HttpClientErrorException {
        System.out.println(task);
        storageApi.rollbackReserve(task.getId());
        accountApi.rollback(new Transaction(Long.parseLong(storageAccount), userId, task));
    }

    @LogMethod
    public void completeTask(Long idTask, Long userId) {
        Task temp = storageApi.getTask(idTask).getBody();
        temp.setStatus(TaskStatus.DONE);
        storageApi.completeTask(idTask, userId);
        accountApi.take(new Transaction(userId, userId, temp));
    }

    @LogMethod
    public void createNew(Task task) {
        storageApi.createTask(task);
    }

    @LogMethod
    public void lazyFill() {
        createNew(new Task("Поменять лампочку"));
        createNew(new Task("Подготовить презентацию"));
        createNew(new Task("Закончить проект"));
    }
}
