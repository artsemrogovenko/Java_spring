package com.example.sem3HomeTask;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/*
a) Добавление пользователя - запрос :
метод - POST
адрес - http://localhost:8080/user/body
тело -
{
"name":"Artur",
"age":23,
"email":"exam1@yandex.ru"
}
b) Получение списка пользователей на сервере - запрос:
метод - GET
адрес - http://localhost:8080/user
c) Проверка сортировки - запрос:
метод - GET
адрес - http://localhost:8080/tasks/sort
d) Проверка фильтрации - запрос:
метод - GET
адрес - http://localhost:8080/tasks/filter/23
e) Проверка среднего арифметического - запрос:
метод - GET
адрес - http://localhost:8080/tasks/calc
*/
@SpringBootApplication
public class Sem3HomeTaskApplication {

	public static void main(String[] args) {
		SpringApplication.run(Sem3HomeTaskApplication.class, args);
		//ConfigurableApplicationContext context = SpringApplication.run(Sem3HomeTaskApplication.class,args);
		//Object dataSource=context.getBean("dataSource");
		//System.out.println(dataSource);
	}

}
