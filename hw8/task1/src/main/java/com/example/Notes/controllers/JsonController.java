package com.example.Notes.controllers;

import com.example.Notes.info.SessionAbout;
import com.example.Notes.model.Note;
import com.example.Notes.service.NoteService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class JsonController {
    private final SessionAbout sessionAbout;
    private final NoteService noteService;

    /**
     * базовые запросы
     */
    @GetMapping
    public ResponseEntity<List<Note>> getAll(HttpServletRequest request) {
        sessionAbout.setId(request.getSession().toString());
        sessionAbout.setUserAgent(request.getHeader("User-Agent") + " " + request.getHeader("Host"));
        return new ResponseEntity<>(noteService.getAllNotes(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Note> createNote(@RequestBody Note note, HttpServletRequest request) {
        sessionAbout.setId(request.getSession().toString());
        sessionAbout.setUserAgent(request.getHeader("User-Agent") + " " + request.getHeader("Host"));
        return new ResponseEntity<>(noteService.createNote(note), HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<Note> getNote(@PathVariable("id") Long id, HttpServletRequest request) {
        sessionAbout.setId(request.getSession().toString());
        sessionAbout.setUserAgent(request.getHeader("User-Agent") + " " + request.getHeader("Host"));
        Note note;
        try {
            note = noteService.getNoteById(id);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Note());
        }
        return new ResponseEntity<>(note, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<Note> updateNote(@RequestBody Note note, HttpServletRequest request) {
        sessionAbout.setId(request.getSession().toString());
        sessionAbout.setUserAgent(request.getHeader("User-Agent") + " " + request.getHeader("Host"));
        return new ResponseEntity<>(noteService.updateNote(note), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteNote(@PathVariable("id") Long id, HttpServletRequest request) {
        sessionAbout.setId(request.getSession().toString());
        sessionAbout.setUserAgent(request.getHeader("User-Agent") + " " + request.getHeader("Host"));
        noteService.deleteNote(id);
        return ResponseEntity.ok().build();
    }

}
