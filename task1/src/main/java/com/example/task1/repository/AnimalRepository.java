package com.example.task1.repository;

import com.example.task1.entity.Animal;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AnimalRepository extends JpaRepository<Animal, Integer> {

    Optional<Animal> findByName(String name);
}
