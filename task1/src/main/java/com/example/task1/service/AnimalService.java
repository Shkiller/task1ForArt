package com.example.task1.service;

import com.example.task1.dto.AnimalDTO;
import com.example.task1.dto.response.ValidationResponse;

import java.security.Principal;
import java.util.Set;


public interface AnimalService {
    AnimalDTO put(AnimalDTO animalDTO, Principal principal) throws Exception;

    ValidationResponse delete(int id, Principal principal) throws Exception;

    Set<AnimalDTO> myAnimals(Principal principal);

    AnimalDTO getAnimal(int id);
}
