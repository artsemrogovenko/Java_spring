package com.example.sem5_springdata_ex2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Используя Spring Boot и Spring Data JPA, реализуйте многотабличную БД с двумя сущностями: "Студент" (Student) и "Курс" (Course).
 * Необходимо реализовать отношение многие ко многим, так чтобы один студент мог посещать несколько курсов, а курсы могли иметь множество студентов.
 */
@SpringBootApplication
public class Sem5SpringdataEx2Application {

    public static void main(String[] args) {
        SpringApplication.run(Sem5SpringdataEx2Application.class, args);
    }

}
