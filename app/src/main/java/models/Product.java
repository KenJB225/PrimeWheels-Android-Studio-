package com.shoppingcart.models;

public class Product {
    private String name;
    private double price;
    private String description;
    private int imageResId; // Image resource ID

    public Product(String name, double price, String description, int imageResId) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.imageResId = imageResId;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

    public int getImageResId() {
        return imageResId;
    }
}
