package com.example.rikky_and_morty_rest.domain;

import com.example.rikky_and_morty_rest.domain.Character.Location;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
public class Result {
    private Integer id;
    private String name;
    private String status;
    private String species;
    private String type;
    private String gender;
    private String image;
    private String url;
    private Date created;

    private List<String> episode;
    private Location location;
    public Result(){}
}
