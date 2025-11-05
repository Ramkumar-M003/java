package model;

import java.io.Serializable;

public class Patient implements Serializable {
    private int id;
    private String name;
    private int age;
    private String gender;
    private String disease;
    private String treatment;

    public Patient(int id, String name, int age, String gender, String disease, String treatment) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.disease = disease;
        this.treatment = treatment;
    }

    public Patient(String name, int age, String gender, String disease, String treatment) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.disease = disease;
        this.treatment = treatment;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public int getAge() { return age; }
    public String getGender() { return gender; }
    public String getDisease() { return disease; }
    public String getTreatment() { return treatment; }

    @Override
    public String toString() {
        return id + " | " + name + " | " + age + " | " + gender + " | " + disease + " | " + treatment;
    }
}
