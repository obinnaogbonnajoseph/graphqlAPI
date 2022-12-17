package com.udacity.doggraphqlapi.repository;

import com.udacity.doggraphqlapi.entity.Dog;
import org.springframework.data.repository.CrudRepository;


public interface DogRepository extends CrudRepository<Dog, Long> {
}
