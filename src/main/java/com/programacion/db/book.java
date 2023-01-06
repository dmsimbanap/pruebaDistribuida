package com.programacion.db;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

public class book {
    @Getter @Setter private Integer id;
    @Getter @Setter private String isbn;
    @Getter @Setter private String title;
    @Getter @Setter private String author;
    @Getter @Setter private Double price;
}
