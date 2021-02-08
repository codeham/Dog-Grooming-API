package com.example.demo.controllers;

import com.example.demo.models.Dog;
import com.example.demo.repositories.DogRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Optional;

import static org.hamcrest.CoreMatchers.containsString;

public class DogControllerTest {

    private MockMvc mockMvc;
    private DogRepository dogRepo;
    private DogController dogController;

    public DogControllerTest(){
        dogRepo = Mockito.mock(DogRepository.class);
        dogController = new DogController(dogRepo);
    }

    @BeforeEach
    public void setup(){
        this.mockMvc = MockMvcBuilders.standaloneSetup(dogController).build();
    }

    @Test
    public void whenDogNotExists_thenHttp404() throws Exception{
        Mockito.doReturn(Optional.empty()).when(dogRepo).findById(1L);
        mockMvc.perform(MockMvcRequestBuilders.get("/dogs/1"))
                .andExpect(MockMvcResultMatchers.status().is(HttpStatus.NOT_FOUND.value()));
    }

    @Test
    public void whenDogExists_thenHttp200_andDogReturned() throws Exception {
        String dogName = "Billy";
        Dog testDog = new Dog();
        testDog.setName(dogName);
        Mockito.doReturn(Optional.of(testDog))
                .when(dogRepo)
                .findById(1L);
        mockMvc.perform(MockMvcRequestBuilders.get("/dogs/1"))
                .andExpect(MockMvcResultMatchers.status().is(HttpStatus.OK.value()))
                .andExpect(MockMvcResultMatchers.content().string(containsString(dogName)));
    }
}
