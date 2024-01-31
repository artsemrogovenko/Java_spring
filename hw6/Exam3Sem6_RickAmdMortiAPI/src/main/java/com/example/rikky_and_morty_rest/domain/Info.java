package com.example.rikky_and_morty_rest.domain;

import lombok.Data;

@Data
public class Info {
    private Integer count;
    private Integer pages;
    private String next;
    private String prev;
}
