package com.example.rikky_and_morty_rest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.client.RestTemplate;

/**
 * На основе проекта показанного на семинаре разработать сайт:
 * - Отображающий список героев мультсериала Rick and Morty(с картинками)
 * - При выборе героя отобразить карточку товара с персонажем(с картинкой)
 * - В проекте использовать шаблонизатор thymeleaf
 * - Магическую строчку вынести в настройки проекта.
 */
@EnableScheduling
@SpringBootApplication
@ConfigurationPropertiesScan("com.example.rikky_and_morty_rest.controller.WebApi")
public class ExampleRikkyAndMortyRestApplication {

	@Bean
	public RestTemplate template(){
		return new RestTemplate();
	}
	@Bean
	public HttpHeaders headers()
	{
		return new HttpHeaders();
	}

	public static void main(String[] args) {
		SpringApplication.run(ExampleRikkyAndMortyRestApplication.class, args);
	}

}
