package com.example.Notes.repository;

import com.example.Notes.model.Note;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.List;


@Repository
public interface NoteRepository extends JpaRepository<Note, Long> {
//   List<com.example.Task1.model.Note> findByStatus(TaskStatus status);
//   @Query(value="UPDATE tasks_table SET status = 'DONE' WHERE id =:id",nativeQuery = true)
//   void setTaskStatusDone(Long id);
}
