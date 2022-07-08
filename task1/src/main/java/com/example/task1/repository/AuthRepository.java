package com.example.task1.repository;


import com.example.task1.entity.Auth;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;


public interface AuthRepository extends JpaRepository<Auth, Integer> {
    @Query("SELECT a " +
            "FROM Auth a " +
            "WHERE a.ip = ?1 AND a.timestamp >= ?2 ")
    List<Auth> findByIpAndTimestamp(String ip, LocalDateTime localDateTime);

    List<Auth> findByIp(String ip);
}
