package com.example.securityweb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class WebController {
    /**
     * начальная страница
     */
    @GetMapping
    public String redirect() {
        return "redirect:/public";
    }

    /**
     * авторизация с возможностью отобразить ошибку
     */
    @GetMapping("/login")
    public String login(@RequestParam(name = "error", required = false) String error, Model model) {
        if (error != null) {
            model.addAttribute("errorMessage", error);
        }
        return "login";
    }

    @GetMapping("/public")
    public String publicPage() {
        return "public-data.html";
    }

    /**
     * закрытая страница
     */
    @GetMapping("/private")
    public String privatePage() {
        return "private-data.html";
    }

}
