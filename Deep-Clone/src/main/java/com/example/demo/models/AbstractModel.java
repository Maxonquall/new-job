package com.example.demo.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


import java.util.Map;

@Setter
@Getter
@AllArgsConstructor
public class AbstractModel {
    private String name;
    private Map<String, String> maps;

    public AbstractModel() {}
}
