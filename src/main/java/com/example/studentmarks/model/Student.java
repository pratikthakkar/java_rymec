package com.example.studentmarks.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private int dataScience;
    private int maths;
    private int python;
    private int dataStructures;

    public Student() {}

    public Student(String name, int dataScience, int maths, int python, int dataStructures) {
        this.name = name;
        this.dataScience = dataScience;
        this.maths = maths;
        this.python = python;
        this.dataStructures = dataStructures;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getDataScience() {
        return dataScience;
    }

    public int getMaths() {
        return maths;
    }

    public int getPython() {
        return python;
    }

    public int getDataStructures() {
        return dataStructures;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDataScience(int dataScience) {
        this.dataScience = dataScience;
    }

    public void setMaths(int maths) {
        this.maths = maths;
    }

    public void setPython(int python) {
        this.python = python;
    }

    public void setDataStructures(int dataStructures) {
        this.dataStructures = dataStructures;
    }
}
