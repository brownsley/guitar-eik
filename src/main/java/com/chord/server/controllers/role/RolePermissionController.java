package com.chord.server.controllers.role;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chord.server.dto.request.role.AssignRoleDto;
import com.chord.server.dto.request.role.PermissionCreateDto;
import com.chord.server.dto.request.role.RoleCreateDto;
import com.chord.server.entities.auth.Permission;
import com.chord.server.entities.auth.Role;
import com.chord.server.services.auth.RolePermissionService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/RPA")
public class RolePermissionController {
    private final RolePermissionService rolePermissionService;

    public RolePermissionController(RolePermissionService rolePermissionService) {
        this.rolePermissionService = rolePermissionService;
    }

    @GetMapping("/permission")
    public List<Permission> getAllPermissions() {
        return rolePermissionService.getAllPermissions();
    }

    @GetMapping("/role")
    public List<Role> getAllRoles() {
        return rolePermissionService.getAllRoles();
    }

    @PostMapping("/assign")
    public void assignRoleToUser(@Valid @RequestBody AssignRoleDto assignRoleDto) {
        rolePermissionService.assignRoleToUser(assignRoleDto);
    }

    @PostMapping("/role")
    public void roleCreate(@Valid @RequestBody RoleCreateDto createDto) {
        rolePermissionService.roleCreate(createDto);
    }

    @PostMapping("permission")
    public void permissionCreate(@Valid @RequestBody PermissionCreateDto createDto) {
        rolePermissionService.permissionCreate(createDto);
    }
}
