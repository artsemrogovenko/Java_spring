package com.example.Task1.repository;

import com.example.Task1.model.Task;
import com.example.Task1.model.TaskStatus;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.List;


@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
   List<Task> findByStatus(TaskStatus status);
   @Query(value="UPDATE tasks_table SET status = 'DONE' WHERE id =:id",nativeQuery = true)
   void setTaskStatusDone(Long id);
}
