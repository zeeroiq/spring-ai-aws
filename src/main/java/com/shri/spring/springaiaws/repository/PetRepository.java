package com.shri.spring.springaiaws.repository;

import com.shri.spring.springaiaws.model.Pet;
import org.springframework.data.repository.ListCrudRepository;

public interface PetRepository extends ListCrudRepository<Pet, Long> {
}
