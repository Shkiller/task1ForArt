package com.example.task1.repository;

import com.example.task1.entity.Animal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface AnimalRepository extends JpaRepository<Animal,Integer> {
    @Query("SELECT a " +
            "FROM Animal a " +
            "WHERE a.name = ?1 ")
    Optional<Animal> findByName(String name);
}
