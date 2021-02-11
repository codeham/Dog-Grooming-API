package com.example.demo.controllers;

import com.example.demo.exceptions.ResourceNotFoundException;
import com.example.demo.models.Dog;
import com.example.demo.repositories.DogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
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
     * POST mapping
     * @param dog
     * @return URI location for Req.
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
     * GET mapping
     * @return all dogs in DB
     */
    @GetMapping("/dogs")
    @ResponseBody
    Iterable<Dog> getAllDogs(){
        return dogRepo.findAll();
    }

    /**
     * GET mapping
     * @param id @Min(1)
     * @return dog found based on id param. provided
     */
    @GetMapping("/dogs/{id}")
    ResponseEntity getDogById(@PathVariable @Min(1) Long id){
        Dog foundDog = dogRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User with ID: " + id + " Not Found!"));

        return ResponseEntity.ok().body(foundDog);
    }

    /**
     * PUT mapping
     * @param id @Min(1)
     * @param updatedDog Dog req. to be updated with existing id
     * @return dog that was updated by id in DB
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
     * DELETE mapping
     * @param id @Min(1)
     * @return message verifying deletion of dog
     */
    @DeleteMapping("/dogs/{id}")
    public ResponseEntity deleteDog(@PathVariable @Min(1) Long id){
        Dog foundDog = dogRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Dog with ID: " + id + " Not Found!"));

        dogRepo.deleteById(foundDog.getId());
        return ResponseEntity.ok().body("Dog successfully deleted!");
    }

    /**
     * GET mapping
     * @param name
     * @param breed
     * @return dog(s) found by attributes @param name and @param breed
     */
    @GetMapping("/dogs/findByNameAndBreed")
    @ResponseBody
    public ResponseEntity findByNameAndBreed(@RequestParam @NotEmpty String name, @RequestParam @NotEmpty String breed){
        List<Dog> dogList = dogRepo.findByNameAndBreed(name, breed);

        if(dogList.isEmpty()){
            throw new ResourceNotFoundException("Dogs by Name: " + name + " and Breed: " + breed + " Not Found!");
        }

        return ResponseEntity.ok().body(dogList);
    }

    /**
     * GET mapping
     * @return dog(s) in ascending order by name attirbute
     */
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
