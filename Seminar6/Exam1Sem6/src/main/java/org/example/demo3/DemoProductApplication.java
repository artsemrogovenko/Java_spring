package org.example.demo3;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
/*Ваша задача - разработать RESTful API для серверного приложения, используя Spring Boot и Spring Web.
API будет предназначено для управления сущностью "Продукт" (Product) в интернет-магазине.
 * */
/**
 * - GET /products - получение списка всех продуктов.
 * - GET /products/{id} - получение продукта по ID.
 * - POST /products - создание нового продукта.
 * - PUT /products/{id} - обновление продукта по ID.
 * - DELETE /products/{id} - удаление продукта по ID.
 * */
@SpringBootApplication
public class DemoProductApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoProductApplication.class, args);
    }

}
