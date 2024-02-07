package com.example.Notes.model;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.Notes.repository.NoteRepository;

import java.util.List;

@Component
public class TableFill {
    @Autowired
    private NoteRepository  noteRepository;

    @PostConstruct
    public void initializeData() {
        // Создаем объекты Task и сохраняем их в репозиторий
        Note note1 = new Note("Сделать домашнюю работу", "Поменять лампочку");
        Note note2 = new Note("Подготовить презентацию", "какую еще презентацию?");
        Note note3 = new Note("Закончить проект", "еще не начат");

        noteRepository.saveAll(List.of(note1, note2, note3));
    }
}
