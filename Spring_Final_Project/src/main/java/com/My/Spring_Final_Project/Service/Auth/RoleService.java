package com.My.Spring_Final_Project.Service.Auth;

import com.My.Spring_Final_Project.DTO.auth.RoleDTO;
import com.My.Spring_Final_Project.Entity.auth.Role;
import com.My.Spring_Final_Project.Enum.Auth.RoleType;
import com.My.Spring_Final_Project.Repository.auth.RoleRepository;
import com.My.Spring_Final_Project.util.NotFoundException;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService {

    private final RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    // ✅ Get all roles
    public List<RoleDTO> findAll() {
        List<Role> roles = roleRepository.findAll(Sort.by("name"));
        return roles.stream()
                .map(role -> mapToDTO(role, new RoleDTO()))
                .toList();
    }

    // ✅ Get role by RoleType
    public RoleDTO get(RoleType roleName) {
        Role role = roleRepository.findByName(roleName)
                .orElseThrow(NotFoundException::new);
        return mapToDTO(role, new RoleDTO());
    }

    // ✅ Create role
    public RoleType create(RoleDTO roleDTO) {
        Role role = new Role();
        mapToEntity(roleDTO, role);
        return roleRepository.save(role).getName();
    }

    // ✅ Update role
    public void update(RoleType roleName, RoleDTO roleDTO) {
        Role role = roleRepository.findByName(roleName)
                .orElseThrow(NotFoundException::new);
        mapToEntity(roleDTO, role);
        roleRepository.save(role);
    }

    // ✅ Delete role
    public void delete(RoleType roleName) {
        Role role = roleRepository.findByName(roleName)
                .orElseThrow(NotFoundException::new);
        roleRepository.delete(role);
    }

    // ================== MAPPERS ==================

    private RoleDTO mapToDTO(Role role, RoleDTO roleDTO) {
        roleDTO.setRoleName(role.getName());
        roleDTO.setRoleDescription(role.getRoleDescription());
        return roleDTO;
    }

    private void mapToEntity(RoleDTO roleDTO, Role role) {
        role.setName(roleDTO.getRoleName());
        role.setRoleDescription(roleDTO.getRoleDescription());
    }

    // ✅ Exists check
    public boolean roleExists(RoleType roleName) {
        return roleRepository.existsByName(roleName);
    }
}
