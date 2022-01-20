package com.example.spring_project1.objectmapper;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
public class Burger {
    private String name;
    private int price;
    private List<String> ingredients;
}