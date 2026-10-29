package com.example.smartpantrymanager_rea;

public class Ingredient {
    public int id;
    public String name;
    public String quantity;
    public String category;
    public String expiry;

    public Ingredient(int id, String name, String quantity,
                      String category, String expiry) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.category = category;
        this.expiry = expiry;
    }
}