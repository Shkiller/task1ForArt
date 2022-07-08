package com.example.task1.service.impl;

import com.example.task1.dto.AnimalDTO;
import com.example.task1.dto.response.ValidationResponse;
import com.example.task1.entity.Animal;
import com.example.task1.entity.Person;
import com.example.task1.entity.Type;
import com.example.task1.exception.AnimalNameExistException;
import com.example.task1.exception.AnimalNotFoundException;
import com.example.task1.exception.TypeNotExistException;
import com.example.task1.exception.UserNotOwnAnimalException;
import com.example.task1.repository.AnimalRepository;
import com.example.task1.repository.PersonRepository;
import com.example.task1.repository.TypeRepository;
import com.example.task1.service.AnimalService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
@AllArgsConstructor
public class AnimalServiceImpl implements AnimalService {

    private final PersonRepository personRepository;
    private final AnimalRepository animalRepository;
    private final TypeRepository typeRepository;

    @Override
    public AnimalDTO add(AnimalDTO animalDTO, Principal principal) throws AnimalNameExistException, TypeNotExistException {
        Person currentPerson = getPerson(principal);
        Type type = null;
        if (animalDTO.getType() != null)
            type = typeRepository.findById(animalDTO.getType()).orElseThrow(TypeNotExistException::new);
        if (animalRepository.findByName(animalDTO.getName()).isPresent())
            throw new AnimalNameExistException();
        animalRepository.save(new Animal()
                .setBirthday(animalDTO.getBirthday())
                .setGender(animalDTO.getGender())
                .setName(animalDTO.getName())
                .setPerson(currentPerson)
                .setType(type));
        return animalDTO;
    }

    @Override
    public AnimalDTO update(AnimalDTO animalDTO, int id, Principal principal) throws UserNotOwnAnimalException, AnimalNotFoundException, TypeNotExistException {
        Person currentPerson = getPerson(principal);
        Type type = null;
        if (animalDTO.getType() != null)
            type = typeRepository.findById(animalDTO.getType()).orElseThrow(TypeNotExistException::new);
        Animal animal = animalRepository.findById(id).orElseThrow(AnimalNotFoundException::new);
        if (currentPerson.equals(animal.getPerson()))
            animalRepository.save(animal
                    .setBirthday(animalDTO.getBirthday())
                    .setGender(animalDTO.getGender())
                    .setName(animalDTO.getName())
                    .setType(type));
        else throw new UserNotOwnAnimalException();
        return animalDTO;
    }

    @Override
    public AnimalDTO delete(int id, Principal principal) throws UserNotOwnAnimalException, AnimalNotFoundException {
        Person currentPerson = getPerson(principal);
        Animal animal = animalRepository.findById(id).orElseThrow(AnimalNotFoundException::new);
        if (currentPerson.equals(animal.getPerson()))
            animalRepository.delete(animal);
        else throw new UserNotOwnAnimalException();


        return new AnimalDTO()
                .setBirthday(animal.getBirthday())
                .setGender(animal.getGender())
                .setName(animal.getName());
    }

    @Override
    public Set<AnimalDTO> myAnimals(Principal principal) {
        Person currentPerson = getPerson(principal);
        Set<AnimalDTO> animals = new HashSet<>();
        currentPerson.getAnimals().forEach(animal -> animals
                .add(new AnimalDTO()
                        .setName(animal.getName())
                        .setGender(animal.getGender())
                        .setBirthday(animal.getBirthday())
                        .setType(animal.getType() == null ? null : animal.getType().getId())));
        return animals;
    }

    @Override
    public AnimalDTO getAnimal(int id) throws AnimalNotFoundException {
        Animal animal = animalRepository.findById(id).orElseThrow(AnimalNotFoundException::new);
        return new AnimalDTO()
                .setName(animal.getName())
                .setGender(animal.getGender())
                .setBirthday(animal.getBirthday())
                .setType(animal.getType() == null ? null : animal.getType().getId());
    }

    private Person getPerson(Principal principal) {
        return personRepository.findByNickname(principal.getName())
                .orElseThrow(() -> new UsernameNotFoundException(principal.getName()));
    }

}
