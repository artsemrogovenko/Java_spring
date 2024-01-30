package com.example.example4sem6_rikky_and_morty_rest.service;

import com.example.example4sem6_rikky_and_morty_rest.domain.Characters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class ServiceApiImpl implements ServiceApi{

    @Autowired
    private RestTemplate template;

    @Autowired
    private HttpHeaders headers;

    @Value("${R&M.path}")
    private String CHARACTER_API;

   // private  static final String CHARACTER_API = "https://rickandmortyapi.com/api/character";
    @Override
    public Characters getAllCharacters(String url) {
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<>(headers);

        String source=(url==null)? CHARACTER_API : url;

        ResponseEntity<Characters> responce = template.exchange(source, HttpMethod.GET,entity, Characters.class);

        return responce.getBody();
    }
}
