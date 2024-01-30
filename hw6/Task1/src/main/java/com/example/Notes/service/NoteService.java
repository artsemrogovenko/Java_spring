package com.example.Notes.service;

import com.example.Notes.model.Note;
import com.example.Notes.repository.NoteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NoteService{
    private final NoteRepository noteRepository;

   
    public List<Note> getAllNotes() {
        return noteRepository.findAll();
    }

   
    public Note getNoteById(Long id) {
        return noteRepository.findById(id).orElseThrow(null);
    }

   
    public Note updateNote(Note note) {
        Note noteById = getNoteById(note.getId());

        noteById.setName(note.getName());
        noteById.setDescription(note.getDescription());


        return noteRepository.save(noteById);
    }

   
    public Note createNote(Note note) {
        return noteRepository.save(note);
    }

   
    public void deleteNote(Long id) {
        Note NoteById = getNoteById(id);
        noteRepository.delete(NoteById);
    }
}