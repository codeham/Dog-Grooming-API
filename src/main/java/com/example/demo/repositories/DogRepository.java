package com.example.demo.repositories;

import com.example.demo.models.Dog;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface DogRepository extends CrudRepository<Dog, Long> {
    List<Dog> findByNameAndBreed(String name, String breed);

    @Query("Select e " + "from Dog e " + "ORDER BY e.name ASC" )
    List<Dog> getByNameInOrder();
}
