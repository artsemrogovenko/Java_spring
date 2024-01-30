package com.example.Notes.controllers;

import com.example.Notes.model.Note;
import com.example.Notes.service.NoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class JsonController {
    private final NoteService noteService;
    @GetMapping
    public ResponseEntity<List<Note>> getAll() {
        return new ResponseEntity<>(noteService.getAllNotes(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Note> createNote(@RequestBody Note note) {
        return new ResponseEntity<>(noteService.createNote(note), HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<Note> getNote(@PathVariable("id") Long id) {
        Note note;
        try {
            note = noteService.getNoteById(id);
        } catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Note());
        }
        return new ResponseEntity<>(note, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<Note> updateNote(@RequestBody Note note) {
        return new ResponseEntity<>(noteService.updateNote(note), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteNote(@PathVariable("id") Long id){
        noteService.deleteNote(id);
        return ResponseEntity.ok().build();
    }

}
