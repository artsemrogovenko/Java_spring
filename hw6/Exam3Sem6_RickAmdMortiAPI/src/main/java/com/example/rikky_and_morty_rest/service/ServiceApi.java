package com.example.rikky_and_morty_rest.service;

import com.example.rikky_and_morty_rest.domain.Characters;
import com.example.rikky_and_morty_rest.domain.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;


import org.springframework.beans.factory.annotation.Value;

import java.util.List;

@Service
public class ServiceApi {

    @Autowired
    private RestTemplate template;
    @Autowired
    private HttpHeaders headers;

    /**
     * @value дефолтный путь
     */
    @Value("${R&M.path}")
    private String CHARACTER_API;


    public Characters getAllCharacters(String url) {
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<>(headers);
        String source = (url == null) ? CHARACTER_API : url;
        ResponseEntity<Characters> responce = template.exchange(source, HttpMethod.GET, entity, Characters.class);
        return responce.getBody();
    }

    public Result getCharacterById(Long id, String url) {
        String urlPathId = UriComponentsBuilder.fromHttpUrl(url)
                .pathSegment(id.toString())
                .toUriString();

        headers.setAccept(List.of(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<Result> response = template.exchange(urlPathId, HttpMethod.GET, entity,Result.class);
        return response.getBody();
    }


}






