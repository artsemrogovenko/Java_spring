package com.example.Notes.service;

import com.example.Notes.aop.TrackUserAction;
import com.example.Notes.model.Note;
import com.example.Notes.repository.NoteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NoteService{
    private final NoteRepository noteRepository;

    @TrackUserAction
    public List<Note> getAllNotes() {
        return noteRepository.findAll();
    }

    @TrackUserAction
    public Note getNoteById(Long id) {
        return noteRepository.findById(id).orElseThrow(null);
    }

    @TrackUserAction
    public Note updateNote(Note note) {
        Note noteById = getNoteById(note.getId());

        noteById.setName(note.getName());
        noteById.setDescription(note.getDescription());


        return noteRepository.save(noteById);
    }
    @TrackUserAction
   
    public Note createNote(Note note) {
        return noteRepository.save(note);
    }

    @TrackUserAction
    public void deleteNote(Long id) {
        Note NoteById = getNoteById(id);
        noteRepository.delete(NoteById);
    }
}