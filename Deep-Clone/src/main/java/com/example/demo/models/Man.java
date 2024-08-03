package com.example.demo.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
public class Man {
    private String name;
    private int age;
    private List<String> favoriteBooks;

    public Man() {}
}
