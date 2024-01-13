package ru.geekbrains.lesson2;

import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Primary
@Profile("local")
public class LocalEngine implements Engine {
    public LocalEngine() {
        System.out.println("Двигатель запущен на моем компе");
    }

    public void go() {
        System.out.println("Поехали медленно");
    }
}
