package com.example.smartpantrymanager_rea;

import java.util.Arrays;
import java.util.List;

public class Recipe {

    public String name;
    public String ingredients;
    public String instructions;

    public Recipe(String name, String ingredients,
                  String instructions) {
        this.name = name;
        this.ingredients = ingredients;
        this.instructions = instructions;
    }

    public List<String> getIngredientList() {
        return Arrays.asList(
                ingredients.toLowerCase().split(",")
        );
    }
}