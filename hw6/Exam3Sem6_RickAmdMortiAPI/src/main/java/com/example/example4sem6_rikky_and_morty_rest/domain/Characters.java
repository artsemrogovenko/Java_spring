package com.example.example4sem6_rikky_and_morty_rest.domain;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.List;

@Data
public class Characters {
     Info info;
     List<Result> results;
}
