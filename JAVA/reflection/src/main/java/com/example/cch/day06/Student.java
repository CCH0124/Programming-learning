package com.example.cch.day06;

public class Student {
    public int age;
    private static String name;

    public Student() {

    }


    public int getAge() {
        return this.age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "{" +
            " age='" + getAge() + "'" +
            "}";
    }

}
