package com.example.rikky_and_morty_rest.domain;

import lombok.Data;

import java.util.List;

@Data
public class Characters {
     Info info;
     List<Result> results;
}
