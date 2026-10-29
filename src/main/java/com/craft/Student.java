package com.craft;

import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@ToString
public class Student {

    private int id;
    private String name;
    private int age;
    private Address address;
    private List<Subject> subjects;

    public Student(int id, String name, int age, Address address) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.address = address;
    }

    public void getHello() {
        System.out.println("Hello Springers");
    }

    public void getDetails() {
        System.out.println("Student Details: " + this);
    }
}