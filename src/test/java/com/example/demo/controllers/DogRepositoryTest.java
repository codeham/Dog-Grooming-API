package com.example.demo.controllers;

import com.example.demo.models.Dog;
import com.example.demo.repositories.DogRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;


@DataJpaTest
public class DogRepositoryTest {

    @Autowired
    private DogRepository dogRepository;

    private Dog dog;

    @BeforeEach
    void setUp(){
        dog = dogRepository.save(new Dog("Rufus", "American Pitbull", 2, 60.0, "brown", "short", true, true));
    }

    @Test
    void createDog(){
        assertNotNull(dog);
    }

    @Test
    void getDogs(){
        List<Dog> dogs = (List<Dog>) dogRepository.findAll();
        assertTrue(!dogs.isEmpty());
    }
}
