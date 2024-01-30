package org.example.demo4;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/*Создайте RESTful API на базе Spring Boot и Spring Web для управления библиотекой.
Основной сущностью будет "Читатель" (Reader) и "Книга" (Book).
Необходимо учитывать, что одна книга может быть взята только одним читателем, но один читатель может взять несколько книг.*/

/**
 *
 - GET /books - получение списка всех книг.
 - GET /books/{id} - получение книги по ID.
 - POST /books - добавление новой книги.
 - PUT /books/{id}/reader/{readerId} - назначение читателя для книги.
 - DELETE /books/{id} - удаление книги.
 - GET /readers - получение списка всех читателей.
 - POST /readers - добавление нового читателя.

 * */
@SpringBootApplication
public class DemoLibraryApplication {
    public static void main(String[] args) {
        SpringApplication.run(DemoLibraryApplication.class, args);
    }

}
