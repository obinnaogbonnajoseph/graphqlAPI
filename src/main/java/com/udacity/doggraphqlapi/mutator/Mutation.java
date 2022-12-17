package com.udacity.doggraphqlapi.mutator;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.udacity.doggraphqlapi.entity.Dog;
import com.udacity.doggraphqlapi.exception.BreedNotFoundException;
import com.udacity.doggraphqlapi.exception.DogNotFoundException;
import com.udacity.doggraphqlapi.repository.DogRepository;
import org.springframework.stereotype.Component;

@Component
public class Mutation implements GraphQLMutationResolver {

    private final DogRepository dogRepository;

    public Mutation(DogRepository dogRepository) {
        this.dogRepository = dogRepository;
    }

    public boolean deleteDogBreed(String breed) {
        boolean deleted = false;
        var allDogs = dogRepository.findAll();
        // Loop through all dogs to check their breed
        for (Dog d:allDogs) {
            if (d.getBreed().equals(breed)) {
                // Delete if the breed is found
                dogRepository.delete(d);
                deleted = true;
            }
        }
        // Throw an exception if the breed doesn't exist
        if (!deleted) {
            throw new BreedNotFoundException("Breed Not Found", breed);
        }
        return deleted;
    }

    public Dog updateDogName(String newName, Long id) {
        var dog = dogRepository.findById(id)
                .orElseThrow(() -> new DogNotFoundException("Dog not Found", id));
        dog.setName(newName);
        dogRepository.save(dog);
        return dog;
    }
}
