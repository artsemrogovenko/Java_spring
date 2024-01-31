package com.example.rikky_and_morty_rest.controller;

import com.example.rikky_and_morty_rest.domain.Characters;
import com.example.rikky_and_morty_rest.domain.Result;
import com.example.rikky_and_morty_rest.service.ServiceApi;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@AllArgsConstructor
public class WebApi {

    @Autowired
    private final ServiceApi serviceApi;

    private final UrlConf urlConf;



    /**
     * Обработчик при входе на страницу без параметров
     *
     * @return страница для карточками
     */
    @GetMapping("/web")
    public String showCharacters(Model model) {
        Characters crts = serviceApi.getAllCharacters(urlConf.getPage());
        model.addAttribute("characters", crts);
        return "app";
    }

    /**
     * @param url веб страница для перехода
     * @return страница с карточками
     */
    @GetMapping("/stepurl")
    public String stepCharacters(@RequestParam String url, Model model) {
        urlConf.setPage(url);
        Characters crts = serviceApi.getAllCharacters(urlConf.getPage());
        model.addAttribute("characters", crts);
        return "app";
    }

    @GetMapping("/back")
    public String backToCharacters() {
        return "redirect:web";
    }

    /**
     * @param id персонаж
     * @param model новое представление для html
     * @return card.html
     */
    @GetMapping(value = "/result/{id}")
    public String infoCharacter(@PathVariable Long id, Model model) {
        Result result = serviceApi.getCharacterById(id, urlConf.getPage());
        model.addAttribute("character", result);
        return "card";
    }


}
