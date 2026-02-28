package com.My.Spring_Final_Project.restController;

import com.My.Spring_Final_Project.DTO.admin.AdminUserStatisticsDTO;
import com.My.Spring_Final_Project.Entity.auth.Role;
import com.My.Spring_Final_Project.Entity.auth.User;
import com.My.Spring_Final_Project.Enum.Auth.RoleType;
import com.My.Spring_Final_Project.Repository.auth.RoleRepository;
import com.My.Spring_Final_Project.Repository.auth.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/admin")
@PreAuthorize("hasAuthority('ROLE_ADMIN')")
public class AdminController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    // ================= GET ALL ROLES =================
    @GetMapping("/roles")
    public ResponseEntity<List<Role>> getAllRoles() {
        return ResponseEntity.ok(roleRepository.findAll());
    }

    // ================= CREATE ROLE =================
    @PostMapping("/roles")
    public ResponseEntity<?> createRole(@RequestParam RoleType roleType,
                                        @RequestParam String description) {

        if (roleRepository.findByName(roleType).isPresent()) {
            return ResponseEntity.badRequest().body("Role already exists");
        }

        Role role = Role.builder()
                .name(roleType)
                .roleDescription(description)
                .build();

        return ResponseEntity.ok(roleRepository.save(role));
    }

    // ================= UPDATE USER ROLES =================
    @PutMapping("/users/{username}/roles")
    public ResponseEntity<?> updateUserRoles(
            @PathVariable String username,
            @RequestBody List<RoleType> roles) {

        User user = userRepository.findById(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Set<Role> newRoles = new HashSet<>();

        for (RoleType roleType : roles) {
            Role role = roleRepository.findByName(roleType)
                    .orElseThrow(() -> new RuntimeException("Role not found: " + roleType));
            newRoles.add(role);
        }

        user.setRoles(newRoles);
        return ResponseEntity.ok(userRepository.save(user));
    }

    // ================= GET USERS BY ROLE =================
    @GetMapping("/roles/{roleType}/users")
    public ResponseEntity<List<User>> getUsersByRole(@PathVariable RoleType roleType) {

        Role role = roleRepository.findByName(roleType)
                .orElseThrow(() -> new RuntimeException("Role not found"));

        List<User> users = userRepository.findAll()
                .stream()
                .filter(user -> user.getRoles().contains(role))
                .collect(Collectors.toList());

        return ResponseEntity.ok(users);
    }

    // ================= SYSTEM STATISTICS =================
    @GetMapping("/statistics")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<AdminUserStatisticsDTO> getSystemStatistics() {

        AdminUserStatisticsDTO dto = new AdminUserStatisticsDTO();
        dto.setTotalUsers(userRepository.count());
        dto.setEnabledUsers(userRepository.countByEnabledTrue());
        dto.setRoles(roleRepository.findAll());

        return ResponseEntity.ok(dto);
    }

}
