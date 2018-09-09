package com.pilicon.collection.dto;


import lombok.Data;

@Data
public class Course {

    private String id;

    private String name;

    public Course(String id, String name) {
        this.id = id;
        this.name = name;
    }



}
