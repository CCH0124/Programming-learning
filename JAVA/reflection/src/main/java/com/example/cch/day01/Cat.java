package com.example.cch.day01;

public class Cat {
    private String name = "Cat";
    public Integer age = 2;

    public Cat () {

    }
    
    public Cat(String name) {
        this.name = name;
    }
    public void eat() {
        System.out.println("eating...");
    }

    public void catching() {
        System.out.println("catching...");
    }
}
