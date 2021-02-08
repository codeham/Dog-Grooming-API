package com.example.demo.controllers;

import com.example.demo.models.Dog;
import com.example.demo.repositories.DogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@Validated
public class DogController {
    private final DogRepository dogRepo;

    @Autowired
    public DogController(DogRepository dogRepo) {
        this.dogRepo = dogRepo;
    }

    /**
     * 200: dog posted to server
     * 500: repo method fails, server issue
     * 400: if @param Dog entity is not validated properly
     *
     * @param dog
     * @return
     */
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

        return new ResponseEntity(newDog, headers, HttpStatus.CREATED);
    }

    /**
     * 200: successful response (including empty result)
     * 500: repo. method fails, server issue
     *
     * @return
     */
    @GetMapping("/dogs")
    @ResponseBody
    ResponseEntity getAllDogs(){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        Iterable<Dog> iterableDog = null;

        try{
            iterableDog = dogRepo.findAll();
        }catch(Exception e){
            return new ResponseEntity(headers, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity(iterableDog, headers, HttpStatus.OK);
    }

    /**
     * 200: successful response
     * 400: if @Min(1) id is not validated properly, id non-existent
     * 404: id not found in repo.
     * @param id
     * @return
     */
    @GetMapping("/dogs/{id}")
    ResponseEntity getDogById(@PathVariable @Min(1) Long id){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        Dog foundDog = null;
       try{
           foundDog = dogRepo.findById(id).get();
       }catch (Exception e){
           return new ResponseEntity(headers, HttpStatus.NOT_FOUND);
       }

       return new ResponseEntity(foundDog, headers, HttpStatus.OK);
    }

    /**
     * 200: @RequestBody Dog entity values successfully replace existing entity values
     * 200: @RequestBody Repo. stores new Dog entity if id is non-existent
     * 400: if @param Dog entity is not validated properly
     * @param id
     * @param updatedDog
     * @return
     */
    @PutMapping("/dogs/{id}")
    ResponseEntity updateDogById(@PathVariable @Min(1) Long id, @Valid @RequestBody Dog updatedDog){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        return dogRepo.findById(id).map(dog -> {
            dog.setName(updatedDog.getName());
            dog.setAge(updatedDog.getAge());
            dog.setBreed(updatedDog.getBreed());
            dog.setWeight(updatedDog.getWeight());
            dog.setColor(updatedDog.getColor());
            dog.setCoatLength(updatedDog.getCoatLength());
            dog.setHouseTrained(updatedDog.isHouseTrained());
            dog.setVaccinationsReady(updatedDog.isVaccinationsReady());
            dogRepo.save(dog);
            return new ResponseEntity(dog, headers, HttpStatus.OK);
        }).orElseGet(() -> {
            updatedDog.setId(id);
            dogRepo.save(updatedDog);
            return new ResponseEntity(updatedDog, headers, HttpStatus.OK);
        });
    }

    /**
     * 200: successful delete
     * 400: if @Min(1) id is not validated properly, id non-existent
     * 404: id not found to delete
     * @param id
     * @return
     */
    @DeleteMapping("/dogs/{id}")
    public ResponseEntity deleteDog(@PathVariable @Min(1) Long id){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        try{
            dogRepo.deleteById(id);
            return new ResponseEntity(headers, HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity(headers, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/dogs/findByNameAndBreed")
    @ResponseBody
    public ResponseEntity findByNameAndBreed(@RequestParam String name, @RequestParam String breed){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        Iterable<Dog> dogList = null;

        try{
            dogList = dogRepo.findByNameAndBreed(name, breed);
        }catch(Exception e){
            return new ResponseEntity(headers, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity(dogList, headers, HttpStatus.OK);
    }

    @GetMapping("/dogs/getByNameInOrder")
    @ResponseBody
    public ResponseEntity getByNameInOrder(){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        Iterable<Dog> dogList = null;

        try{
            dogList = dogRepo.getByNameInOrder();
        }catch(Exception e){
            return new ResponseEntity(headers, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity(dogList, headers, HttpStatus.OK);
    }
}
