package com.hospital.model;

public class Patient {
    private String name;
    private int age;
    private String symptom;

    public Patient(String name, int age, String symptom) {
        this.name = name;
        this.age = age;
        this.symptom = symptom;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getSymptom() {
        return symptom;
    }
}