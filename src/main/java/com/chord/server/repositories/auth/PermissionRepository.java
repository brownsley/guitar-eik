package com.chord.server.repositories.auth;

import org.springframework.data.jpa.repository.JpaRepository;

import com.chord.server.entities.auth.Permission;

public interface PermissionRepository extends JpaRepository<Permission, Long> {
    boolean existsByName(String name);
}
