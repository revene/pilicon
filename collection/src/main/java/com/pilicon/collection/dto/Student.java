package com.pilicon.collection.dto;

import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
public class Student {

    private String id;

    private String name;

    private Set<Course> courses;

    public Student(String id, String name) {
        this.id = id;
        this.name = name;
        this.courses = new HashSet<>();
    }
}
