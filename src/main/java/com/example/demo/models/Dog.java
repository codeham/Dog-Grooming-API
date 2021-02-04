package com.example.demo.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Dog {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String breed;
    private String age;
    private double weight;
    private String color;
    private String coatLength;
    private boolean isHouseTrained;
    private boolean isVaccinationsReady;

    public Dog(){}

    public Dog(String name, String breed, String age, double weight, String color, String coatLength, boolean houseTrained, boolean vaccinationsReady) {
        this.name = name;
        this.breed = breed;
        this.age = age;
        this.weight = weight;
        this.color = color;
        this.coatLength = coatLength;
        this.isHouseTrained = houseTrained;
        this.isVaccinationsReady = vaccinationsReady;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getCoatLength() {
        return coatLength;
    }

    public void setCoatLength(String coatLength) {
        this.coatLength = coatLength;
    }

    public boolean isHouseTrained() {
        return isHouseTrained;
    }

    public void setHouseTrained(boolean houseTrained) {
        this.isHouseTrained = houseTrained;
    }

    public boolean isVaccinationsReady() {
        return isVaccinationsReady;
    }

    public void setVaccinationsReady(boolean vaccinationsReady) {
        this.isVaccinationsReady = vaccinationsReady;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Dog{");
        sb.append("id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append(", breed='").append(breed).append('\'');
        sb.append(", age='").append(age).append('\'');
        sb.append(", weight=").append(weight);
        sb.append(", color='").append(color).append('\'');
        sb.append(", coatLength='").append(coatLength).append('\'');
        sb.append(", houseTrained=").append(isHouseTrained);
        sb.append(", vaccinationsReady=").append(isVaccinationsReady);
        sb.append('}');
        return sb.toString();
    }
}
