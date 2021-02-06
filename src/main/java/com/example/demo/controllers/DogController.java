package com.example.demo.controllers;

import com.example.demo.models.Dog;
import com.example.demo.repositories.DogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
public class DogController {

    @Autowired
    DogRepository dogRepo;

    @PostMapping("/dogs")
    ResponseEntity createNewDog(@Valid @RequestBody Dog dog){
        Dog newDog = null;
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        try{
            newDog = dogRepo.save(dog);
        }catch(Exception e){
            return new ResponseEntity(headers, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity(newDog, headers, HttpStatus.OK);
    }

    @GetMapping("/dogs")
    @ResponseBody
    Iterable<Dog> getAllDogs(){
        Iterable<Dog> iterable = dogRepo.findAll();
        for(Dog x: iterable){
           System.out.println(x.toString());
           System.out.println();
        }
       return dogRepo.findAll();
    }

//    @GetMapping("/dogs/{id}")
//    Dog singleDog(@PathVariable Long id){
//        return dogRepo.findById(id).orElseThrow();
//    }

    @GetMapping("/dogs/{id}")
    ResponseEntity singleDog(@PathVariable Long id){
       Dog foundDog = dogRepo.findById(id).get();
       HttpHeaders headers = new HttpHeaders();
       headers.setContentType(MediaType.APPLICATION_JSON);
       return new ResponseEntity(foundDog, headers, HttpStatus.OK);
    }

    @DeleteMapping("/dogs/{id}")
    @CrossOrigin
    void deleteBook(@PathVariable Long id){
        dogRepo.deleteById(id);
    }


}
