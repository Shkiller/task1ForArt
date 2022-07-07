package com.example.task1.repository;

import com.example.task1.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PersonRepository extends JpaRepository<Person,Integer> {
    @Query("SELECT p " +
            "FROM Person p " +
            "WHERE p.nickname = ?1 ")
    Optional<Person> findByNickname(String nickname);
}
