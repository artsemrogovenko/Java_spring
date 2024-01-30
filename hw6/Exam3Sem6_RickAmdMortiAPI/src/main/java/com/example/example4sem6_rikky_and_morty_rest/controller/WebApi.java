package com.example.example4sem6_rikky_and_morty_rest.controller;

import com.example.example4sem6_rikky_and_morty_rest.domain.Characters;
import com.example.example4sem6_rikky_and_morty_rest.service.ServiceApi;
import com.example.example4sem6_rikky_and_morty_rest.service.UpdateUrl;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@AllArgsConstructor
public class WebApi {
    @Autowired
    private ServiceApi serviceApi;

    @Autowired
    private UpdateUrl updateCharacterApi;

    @GetMapping("/web")
    public String showCharacters(Model model) {
        Characters crts = serviceApi.getAllCharacters(null);
        model.addAttribute("characters", crts);
        return "app";
    }

    @GetMapping("/stepurl")
    public  String updateCharacterApi(@RequestParam String url , Model model) {
            Characters crts = serviceApi.getAllCharacters(url);
            model.addAttribute("characters", crts);
        return "app";
        }
//    }
//    @PostMapping("/updateCharacterApi/")
//    public ResponseEntity<String> updateCharacterApi(@RequestBody String value) {
//        try {
//            updateCharacterApi.updateCharacterApi(value);
//            return ResponseEntity.ok("Success");
//        } catch (Exception e) {
//            e.printStackTrace(); // Логирование ошибки в консоль
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error");
//        }
//    }
}
