package com.chord.server.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.chord.server.entities.auth.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
    boolean existsByName(String name);
}
