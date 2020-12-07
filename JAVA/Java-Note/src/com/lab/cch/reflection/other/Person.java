package com.lab.cch.reflection.other;
@MyAnnotation(value = "hi")
public class Person extends Creature<String> implements Comparable<String>, MyInterface {
    private String name;
    int age;
    public int id;

    public Person(){

    }
    @MyAnnotation(value = "hi")
    private Person(String name){
        this.name = name;
    }
    Person(String name, int age){
        this.name = name;
        this.age = age;
    }

    @MyAnnotation
    private String show(String nation){
        System.out.println("City: " +nation);
        return nation;
    }
    public String display(String insterets){
        return insterets;
    }
    @Override
    public void info() {
        System.out.println("man");
    }

    @Override
    public int compareTo(String o) {
        return 0;
    }
}
