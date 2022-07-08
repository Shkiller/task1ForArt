package com.example.task1.controller;

import com.example.task1.dto.AnimalDTO;
import com.example.task1.dto.response.ValidationResponse;
import com.example.task1.exception.AnimalNameExistException;
import com.example.task1.exception.AnimalNotFoundException;
import com.example.task1.exception.UserNotOwnAnimalException;
import com.example.task1.service.AnimalService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Set;


@RestController
@AllArgsConstructor
@RequestMapping("/animal")
public class AnimalController {

    private final AnimalService animalService;

    @PostMapping("/add")
    @PreAuthorize("hasAuthority('user:write')")
    public AnimalDTO add(@RequestBody AnimalDTO animalDTO, Principal principal) throws UserNotOwnAnimalException, AnimalNameExistException {
        return animalService.add(animalDTO, principal);
    }

    @PostMapping("/update/{id}")
    @PreAuthorize("hasAuthority('user:write')")
    public AnimalDTO update(@RequestBody AnimalDTO animalDTO, @PathVariable int id, Principal principal) throws UserNotOwnAnimalException, AnimalNotFoundException {
        return animalService.update(animalDTO, id, principal);
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasAuthority('user:write')")
    public ValidationResponse delete(@PathVariable int id, Principal principal) throws UserNotOwnAnimalException {
        return animalService.delete(id, principal);
    }

    @GetMapping("/my")
    @PreAuthorize("hasAuthority('user:write')")
    public Set<AnimalDTO> myAnimals(Principal principal) {
        return animalService.myAnimals(principal);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('user:write')")
    public AnimalDTO getAnimal(@PathVariable int id) throws AnimalNotFoundException {
        return animalService.getAnimal(id);
    }

}
