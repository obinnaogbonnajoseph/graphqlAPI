package com.udacity.doggraphqlapi.resolver;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.udacity.doggraphqlapi.entity.Dog;
import com.udacity.doggraphqlapi.exception.DogNotFoundException;
import com.udacity.doggraphqlapi.repository.DogRepository;
import org.springframework.stereotype.Component;

@Component
public class Query implements GraphQLQueryResolver {

    private final DogRepository dogRepository;

    public Query(DogRepository dogRepository) {
        this.dogRepository = dogRepository;
    }

    public Dog findDogById(Long id) {
        return dogRepository.findById(id).orElseThrow(() -> new DogNotFoundException("Dog not found", id));
    }

    public Iterable<Dog> findAllDogs() {
        return dogRepository.findAll();
    }
}
