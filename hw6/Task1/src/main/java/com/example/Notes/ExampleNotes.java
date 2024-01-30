package com.example.Notes;

import com.example.Notes.model.Note;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class ExampleNotes {

	Note a= new Note("aaa","bbb");

	public static void main(String[] args) {
		SpringApplication.run(ExampleNotes.class, args);
	}

}
