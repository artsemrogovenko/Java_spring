package com.taskstorage.services;

import com.taskstorage.aop.TrackUserAction;
import com.taskstorage.models.Task;
import com.taskstorage.models.TaskStatus;
import com.taskstorage.models.exceptions.ExcessAmountException;
import com.taskstorage.models.exceptions.ResourceNotFoundException;
import com.taskstorage.repositories.TaskRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Сервис для работы с задачими.
 */
@Service
@AllArgsConstructor
@Transactional(readOnly = true)
public class TaskService {
    /**
     * Объект репозитория.
     */
    private final TaskRepository taskRepository;


    /**
     * Получение всех задач.
     *
     * @return список задач.
     */
    @TrackUserAction
    public List<Task> getAllTask() {
        return taskRepository.findAll();
    }

    /**
     * Получение данных о конкретной задаче на доске.
     *
     * @param id идентификатор задачи.
     * @return объект задачи.
     * @throws ResourceNotFoundException исключение при отсутствии задачи.
     */
    @TrackUserAction
    public Task getTaskById(Long id) throws ResourceNotFoundException {
        return taskRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Товар " + id + " не найден!"));
    }


    /**
     * Резервирование задачи на доске.
     */
    @TrackUserAction
    @Transactional
    public void reservedTask(Long id, Long userid) throws ExcessAmountException {
        Task work = getTaskById(id);
        if (work.isReserved())
            throw new ExcessAmountException("задача уже выполняется");
        work.setReserved(true);
        work.setOwner(userid);
        work.setStatus(TaskStatus.IN_PROGRESS);
        taskRepository.save(work);
        System.out.println("reservedTask");
    }

    /**
     * Откат резервирования
     */
    @TrackUserAction
    @Transactional
    public void rollbackReservedTask(Long id) {
        Task work = getTaskById(id);
        work.setReserved(false);
        work.setOwner(1L); // сделать владельцем общее хранилище
        work.setStatus(TaskStatus.TO_DO);
        taskRepository.save(work);
    }

    @TrackUserAction
    public void completedTask(Long id, Long userId) {
        Task work = getTaskById(id);
        if (work.getOwner().equals(userId)) {
            work.setStatus(TaskStatus.DONE);
            taskRepository.save(work);
            System.out.println("Блок complete применился");
        } else
            System.out.println("Блок complete не применился");
    }


}
