package com.example.cch.day05;

public class User {
    private int age;
    private String name;

    public User(String name) {
        this.name = name;
    }

    private User(int age, String name) {
        this.age = age;
        this.name = name;
    }


    public User() {
    }

    public int getAge() {
        return this.age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "{" +
            " age='" + getAge() + "'" +
            ", name='" + getName() + "'" +
            "}";
    }

}
