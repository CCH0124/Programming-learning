package com.example.cch.day03;

public class Car {
    public String brand = "三菱";
    public int price = 100000;
    public String color = "red";

    public String getBrand() {
        return this.brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public int getPrice() {
        return this.price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getColor() {
        return this.color;
    }

    public void setColor(String color) {
        this.color = color;
    }



    @Override
    public String toString() {
        return "{" +
            " brand='" + getBrand() + "'" +
            ", price='" + getPrice() + "'" +
            ", color='" + getColor() + "'" +
            "}";
    }
    
}
