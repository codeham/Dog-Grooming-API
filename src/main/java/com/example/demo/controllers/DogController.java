package com.example.demo.controllers;

import com.example.demo.exceptions.ResourceNotFoundException;
import com.example.demo.models.Dog;
import com.example.demo.repositories.DogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/")
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
        Dog newDog = dogRepo.save(dog);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(newDog.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }

    /**
     * 200: successful response (including empty result)
     * 500: repo. method fails, server issue
     *
     * @return
     */
    @GetMapping("/dogs")
    @ResponseBody
    Iterable<Dog> getAllDogs(){
        return dogRepo.findAll();
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
        Dog foundDog = dogRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User with ID: " + id + " Not Found!"));

        return ResponseEntity.ok().body(foundDog);
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

        Dog foundDog = dogRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User with ID: " + id + " Not Found!"));

        updatedDog.setId(foundDog.getId());
        dogRepo.save(updatedDog);
        return ResponseEntity.ok().body(updatedDog);
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
        Dog foundDog = dogRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Dog with ID: " + id + " Not Found!"));

        dogRepo.deleteById(foundDog.getId());
        return ResponseEntity.ok().body("Dog successfully deleted!");
    }

    @GetMapping("/dogs/findByNameAndBreed")
    @ResponseBody
    public ResponseEntity findByNameAndBreed(@RequestParam String name, @RequestParam String breed){
        List<Dog> dogList = dogRepo.findByNameAndBreed(name, breed);

        if(dogList.isEmpty()){
            throw new ResourceNotFoundException("Dogs by Name: " + name + " and Breed: " + breed + " Not Found!");
        }

        return ResponseEntity.ok().body(dogList);
    }

    @GetMapping("/dogs/getByNameInOrder")
    @ResponseBody
    public ResponseEntity getByNameInOrder(){
        List<Dog> dogList = dogRepo.getByNameInOrder();

        if(dogList.isEmpty()){
            throw new ResourceNotFoundException("Dogs Sorted by Name Not Found!");
        }

        return ResponseEntity.ok().body(dogList);
    }
}
