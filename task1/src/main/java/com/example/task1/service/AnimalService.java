package com.example.task1.service;

import com.example.task1.dto.AnimalDTO;
import com.example.task1.dto.response.ValidationResponse;
import com.example.task1.exception.AnimalNameExistException;
import com.example.task1.exception.AnimalNotFoundException;
import com.example.task1.exception.UserNotOwnAnimalException;

import java.security.Principal;
import java.util.Set;


public interface AnimalService {
    /**
     * method for add animal
     *
     * @param animalDTO - animal for add
     * @param principal - current user
     * @return - result animal
     */
    AnimalDTO add(AnimalDTO animalDTO, Principal principal) throws AnimalNameExistException;

    /**
     * method for update animal
     *
     * @param animalDTO - animal for update
     * @param id        - animal id
     * @param principal - current user
     * @return - result animal
     */
    AnimalDTO update(AnimalDTO animalDTO, int id, Principal principal) throws UserNotOwnAnimalException, AnimalNotFoundException;

    /**
     * method for delete animal
     *
     * @param id        - animal id
     * @param principal - current user
     * @return - deleted animal
     */
    AnimalDTO delete(int id, Principal principal) throws UserNotOwnAnimalException, AnimalNotFoundException;

    /**
     * method for get user animals
     *
     * @param principal - current user
     * @return - user animals
     */
    Set<AnimalDTO> myAnimals(Principal principal);

    /**
     * method for get animal
     *
     * @param id - animal id
     * @return - animal
     */
    AnimalDTO getAnimal(int id) throws AnimalNotFoundException;
}
