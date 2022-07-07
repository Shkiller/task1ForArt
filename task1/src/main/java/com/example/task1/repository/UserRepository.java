package com.example.task1.repository;

import com.example.task1.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
    @Query("SELECT u " +
            "FROM User u " +
            "WHERE u.nickname = ?1 ")
    Optional<User> findByNickname(String nickname);
}
