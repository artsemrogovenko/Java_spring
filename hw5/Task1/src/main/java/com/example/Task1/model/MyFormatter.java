package com.example.Task1.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class MyFormatter {

    public static LocalDateTime formatdate(LocalDateTime localDateTime) {
        return LocalDateTime.parse(localDateTime.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")));
    }

}
