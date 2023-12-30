package ru.geekbrains;

import com.google.gson.Gson;


public final class App {

    public static void main(String[] args) {

        Person person1 = new Person("John", "Cohen", 30);

        // Инициализация библиотеки Gson
        Gson gson = new Gson();
        // Сериализация объекта в JSON
        String json = gson.toJson(person1);
        System.out.println("Сериализованный JSON: " + json);

        // Десериализация JSON в объект Person
        Person deserializedPerson = new Gson().fromJson(json, Person.class);
        System.out.printf("Десериализованный объект: %s %s %d\n", deserializedPerson.getFirstName(), deserializedPerson.getLastName(), deserializedPerson.getAge());

    }
}
