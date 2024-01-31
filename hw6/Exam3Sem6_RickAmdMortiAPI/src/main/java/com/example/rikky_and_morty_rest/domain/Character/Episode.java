package com.example.rikky_and_morty_rest.domain.Character;


import com.example.rikky_and_morty_rest.domain.Result;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;

@Data
public class Episode   {

	String name;
	LocalDate air_date;
	String episode;
	String url;
	List<Result> characters;


}
