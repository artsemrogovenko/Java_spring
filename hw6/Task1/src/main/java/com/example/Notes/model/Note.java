package com.example.Notes.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


@Data
@Entity
@Table(name = "notes")
@NoArgsConstructor
public class Note {


//    public String currentTime() {
//        return LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));
//    }
//
//    public void setComplete() {
//        if (this.status != NoteStatus.DONE) {
//            this.status = NoteStatus.DONE;
//        }
//    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name" , columnDefinition = "VARCHAR(255) NOT NULL")
    private String name;
    @Column(name = "description" , columnDefinition = "VARCHAR(255) NOT NULL")
    private String description;
    @CreationTimestamp
    @Column(name = "created", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime created;

//    @PrePersist
//    protected void onCreate() {
//        if (this.status == null) {
//            this.status = NoteStatus.TO_DO;
//        }
//    }

    public Note(String newName, String newDescription){
        this.description=newDescription;
        this.name=newName;
    }

}
