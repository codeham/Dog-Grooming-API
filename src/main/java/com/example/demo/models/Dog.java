package com.example.demo.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;


@Entity
public class Dog {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull(message = "Name cannot be null")
    @NotEmpty(message = "Name cannot be empty")
    private String name;

    @NotNull(message = "Breed cannot be null")
    @NotEmpty(message = "Breed cannot be empty")
    private String breed;

    @NotNull(message = "Age cannot be null")
    @PositiveOrZero(message = "Age should not be less than 0")
    private int age;

    private double weight;

    private String color;

    private String coatLength;

    private boolean isHouseTrained;

    @NotNull(message = "Vaccinations indicator cannot be null")
    private boolean isVaccinationsReady;

    public Dog(){}

    public Dog(String name, String breed, int age, double weight, String color, String coatLength, boolean houseTrained, boolean vaccinationsReady) {
        this.name = name;
        this.breed = breed;
        this.age = age;
        this.weight = weight;
        this.color = color;
        this.coatLength = coatLength;
        this.isHouseTrained = houseTrained;
        this.isVaccinationsReady = vaccinationsReady;
    }

    public Long getId() { return id; }

    public void setId(Long id){ this.id = id;}

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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
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
