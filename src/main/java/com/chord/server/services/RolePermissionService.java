package com.chord.server.services;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.chord.server.dto.request.role.AssignRoleDto;
import com.chord.server.dto.request.role.PermissionCreateDto;
import com.chord.server.dto.request.role.RoleCreateDto;
import com.chord.server.entities.auth.Permission;
import com.chord.server.entities.auth.Role;
import com.chord.server.entities.auth.User;
import com.chord.server.exception.ResourceAlreadyExistsException;
import com.chord.server.exception.ResourceNotFoundException;
import com.chord.server.repositories.PermissionRepository;
import com.chord.server.repositories.RoleRepository;
import com.chord.server.repositories.UserRepository;

import jakarta.transaction.Transactional;

@Service
public class RolePermissionService {
    private final PermissionRepository permissionRepository;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    public RolePermissionService(RoleRepository roleRepository, UserRepository userRepository,
            PermissionRepository permissionRepository) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
        this.permissionRepository = permissionRepository;
    }

    public List<Permission> getAllPermissions() {
        return permissionRepository.findAll();
    }

    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }

    @Transactional
    public void assignRoleToUser(AssignRoleDto assignRoleDto) {
        User user = userRepository.findByEmployeeId(assignRoleDto.getEmployeeId())
                .orElseThrow(() -> new ResourceNotFoundException("User Not Found"));
        List<Role> rolesToAdd = roleRepository.findAllById(assignRoleDto.getRoles());
        if (rolesToAdd.isEmpty()) {
            throw new RuntimeException("ပေးထားသော ID များဖြင့် Role တစ်ခုမှ ရှာမတွေ့ပါ");
        }
        Set<Role> updatedRoles = new HashSet<>(user.getRoles());
        updatedRoles.addAll(rolesToAdd);
        user.setRoles(updatedRoles);
        userRepository.save(user);
    }

    @Transactional
    public void roleCreate(RoleCreateDto createDto) {
        String roleName = "ROLE_" + createDto.getRole().toUpperCase();
        if (roleRepository.existsByName(roleName)) {
            throw new ResourceAlreadyExistsException("Role already exists");
        }
        Role role = new Role();
        role.setName(roleName);
        List<Permission> permissions = permissionRepository.findAllById(createDto.getPermission());
        if (permissions.isEmpty()) {
            throw new RuntimeException("အနည်းဆုံး Permission တစ်ခုတော့ ရွေးပေးရပါမယ်");
        }
        role.setPermissions(new HashSet<>(permissions));
        roleRepository.save(role);
    }

    @Transactional
    public void permissionCreate(PermissionCreateDto createDto) {
        if (permissionRepository.existsByName(createDto.getPermission())) {
            throw new ResourceAlreadyExistsException("permissions alreadt exists");
        }
        Permission permission = new Permission();
        permission.setName(createDto.getPermission());
        permissionRepository.save(permission);
    }
}
