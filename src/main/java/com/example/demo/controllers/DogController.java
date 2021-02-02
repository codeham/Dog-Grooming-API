package com.example.demo.controllers;

import com.example.demo.models.Dog;
import com.example.demo.repositories.DogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class DogController {

    @Autowired
    DogRepository dogRepo;

    @PostMapping("/dogs")
    Dog newDog(@RequestBody Dog newDog){
        return dogRepo.save(newDog);
    }

    @GetMapping("/dogs")
    @ResponseBody
    Iterable<Dog> allDogs(){
        return dogRepo.findAll();
    }


}
