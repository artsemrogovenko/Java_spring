package ru.geekbrains.lesson2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
class CarController {
    @Autowired
    private
    Car car;

    @GetMapping("/car")
    public String startCar() {
        car.start();
        return "Авто запущен";
    }
}
