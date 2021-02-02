package com.example.demo.controllers;

import com.example.demo.domain.Dog;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DogController {

    @PostMapping("/dogs")
    Dog newDog(@RequestBody Dog newDog){
        return null;
    }


}
