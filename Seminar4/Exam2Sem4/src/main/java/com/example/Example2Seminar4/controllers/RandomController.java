package com.example.Example2Seminar4.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Random;

@Controller
public class RandomController {
/** значения из файла properties
 * если значение переменной в фале не заданы, будут взяты значения из анотаций
 * */
    @Value("${data.parameter.min:2}")// :2 дефолт значение
    private int magicMin;
    @Value("${data.parameter.max:5}")//:5 дефолт значение
    private int magicMax;

    @GetMapping("/random-magic")//http://localhost:8080/random-magic
    public String getRandomNumber( Model model) {
        model.addAttribute("number", new Random().nextInt(100) + 1);
        model.addAttribute("min",magicMin);
        model.addAttribute("max",magicMax);
        return "random";
    }

    @GetMapping("/random/{min}/{max}")//http://localhost:8080/random/2/25
    public String getByReqest(@PathVariable("min") int min, @PathVariable("max") int max, Model model) {
        Random random = new Random();
        model.addAttribute("min",min);
        model.addAttribute("max",max);
        model.addAttribute("number", random.nextInt(min,max+1) );
        return "random";
    }

    @GetMapping("/random")//http://localhost:8080/random?min=2&max=233
    public String getByReqest2(@RequestParam("min") Integer min, @RequestParam("max") Integer max, Model model) {

        model.addAttribute("min",min);
        model.addAttribute("max",max);
        model.addAttribute("number", new Random().nextInt(min,max+1) );
        return "random";
    }

}
