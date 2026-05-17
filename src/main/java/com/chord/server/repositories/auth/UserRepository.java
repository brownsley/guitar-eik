package com.chord.server.repositories.auth;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.chord.server.entities.auth.User;

public interface UserRepository extends JpaRepository<User, Long> {

    boolean existsByEmployeeId(String employeeId);

    Optional<User> findByEmployeeId(String employeeId);
}
