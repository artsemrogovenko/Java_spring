package com.example.rikky_and_morty_rest.domain.Character;


import com.example.rikky_and_morty_rest.domain.Result;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.Getter;

import java.util.List;

@Data
public class Location {
    String name;
    String url;

    String type;

    String dimension;

    List<Result> residents;


}
