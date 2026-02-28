package com.My.Spring_Final_Project.Service.Auth;

import com.My.Spring_Final_Project.DTO.user.UserDTO;
import com.My.Spring_Final_Project.Entity.auth.Role;
import com.My.Spring_Final_Project.Entity.auth.User;
import com.My.Spring_Final_Project.Enum.Auth.RoleType;
import com.My.Spring_Final_Project.Repository.auth.RoleRepository;
import com.My.Spring_Final_Project.Repository.auth.UserRepository;
import com.My.Spring_Final_Project.util.NotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository,
                       RoleRepository roleRepository,
                       PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    // ================= INIT DATA =================
    public void initRoleAndUser() {

        Role adminRole = roleRepository.findByName(RoleType.ROLE_ADMIN)
                .orElseGet(() -> roleRepository.save(
                        Role.builder()
                                .name(RoleType.ROLE_ADMIN)
                                .roleDescription("Admin role")
                                .build()
                ));

        Role userRole = roleRepository.findByName(RoleType.ROLE_USER)
                .orElseGet(() -> roleRepository.save(
                        Role.builder()
                                .name(RoleType.ROLE_USER)
                                .roleDescription("User role")
                                .build()
                ));

        Role moderatorRole = roleRepository.findByName(RoleType.ROLE_MODERATOR)
                .orElseGet(() -> roleRepository.save(
                        Role.builder()
                                .name(RoleType.ROLE_MODERATOR)
                                .roleDescription("Moderator role")
                                .build()
                ));


        if (!userRepository.existsByUserNameIgnoreCase("admin123")) {

            User admin = new User();
            admin.setUserName("admin123");
            admin.setPassword(passwordEncoder.encode("admin@pass"));
            admin.setUserFirstName("Admin");
            admin.setUserLastName("Moderator");
            admin.setEmail("mahmudajannat890@gmail.com");

            admin.setEnabled(true);
            admin.setAccountNonExpired(true);
            admin.setCredentialsNonExpired(true);
            admin.setAccountNonLocked(true);

            admin.setRoles(Set.of(adminRole));

            userRepository.save(admin);
        }

        if (!userRepository.existsByUserNameIgnoreCase("jannat123")) {

            User user = new User();
            user.setUserName("jannat123");
            user.setPassword(passwordEncoder.encode("jannat@pass"));
            user.setUserFirstName("jannat");
            user.setUserLastName("user");
            user.setEmail("jannat@gmail.com");

            user.setEnabled(true);
            user.setAccountNonExpired(true);
            user.setCredentialsNonExpired(true);
            user.setAccountNonLocked(true);

            user.setRoles(Set.of(userRole));

            userRepository.save(user);
        }


        if (!userRepository.existsByUserNameIgnoreCase("mod123")) {

            User moderator = new User();
            moderator.setUserName("mod123");
            moderator.setPassword(passwordEncoder.encode("mod@pass"));
            moderator.setUserFirstName("Moderator");
            moderator.setUserLastName("Admin");
            moderator.setEmail("mod@gmail.com");

            moderator.setEnabled(true);
            moderator.setAccountNonExpired(true);
            moderator.setCredentialsNonExpired(true);
            moderator.setAccountNonLocked(true);

            moderator.setRoles(Set.of(moderatorRole));

            userRepository.save(moderator);
        }


    }

    // ================= CRUD =================
    public List<UserDTO> findAll() {
        return userRepository.findAll(Sort.by("userName"))
                .stream()
                .map(user -> mapToDTO(user, new UserDTO()))
                .toList();
    }

    public UserDTO get(String userName) {
        return userRepository.findById(userName)
                .map(user -> mapToDTO(user, new UserDTO()))
                .orElseThrow(() -> new NotFoundException("User not found"));
    }

    public String create(UserDTO dto) {

        User user = new User();
        mapToEntity(dto, user);
        user.setUserName(dto.getUserName());

        // default flags
        user.setEnabled(true);
        user.setAccountNonExpired(true);
        user.setCredentialsNonExpired(true);
        user.setAccountNonLocked(true);

        // default role
        if (user.getRoles() == null || user.getRoles().isEmpty()) {
            Role userRole = roleRepository.findByName(RoleType.ROLE_USER)
                    .orElseThrow(() -> new NotFoundException("ROLE_USER not found"));
            user.setRoles(Set.of(userRole));
        }

        return userRepository.save(user).getUserName();
    }

    public void update(String userName, UserDTO dto) {

        User user = userRepository.findById(userName)
                .orElseThrow(() -> new NotFoundException("User not found"));

        mapToEntity(dto, user);
        userRepository.save(user);
    }

    public void delete(String userName) {
        if (!userRepository.existsById(userName)) {
            throw new NotFoundException("User not found");
        }
        userRepository.deleteById(userName);
    }

    // ================= PAGINATION =================
    public Page<UserDTO> getAllUsers(int page, int size, String sortBy, String sortDir) {

        Sort sort = sortDir.equalsIgnoreCase("desc")
                ? Sort.by(sortBy).descending()
                : Sort.by(sortBy).ascending();

        Pageable pageable = PageRequest.of(page, size, sort);

        return userRepository.findAll(pageable)
                .map(user -> mapToDTO(user, new UserDTO()));
    }

    // ================= MAPPERS/HELPERS =================
    private UserDTO mapToDTO(User user, UserDTO dto) {

        dto.setUserName(user.getUserName());
        dto.setUserFirstName(user.getUserFirstName());
        dto.setUserLastName(user.getUserLastName());
        dto.setEmail(user.getEmail());
        dto.setEnabled(user.getEnabled());
        dto.setAccountNonExpired(user.getAccountNonExpired());
        dto.setAccountNonLocked(user.getAccountNonLocked());
        dto.setCredentialsNonExpired(user.getCredentialsNonExpired());

        dto.setRoles(
                user.getRoles().stream()
                        .map(role -> role.getName().name())
                        .toList()
        );

        return dto;
    }

    private void mapToEntity(UserDTO dto, User user) {

        user.setUserFirstName(dto.getUserFirstName());
        user.setUserLastName(dto.getUserLastName());
        user.setEmail(dto.getEmail());
        user.setEnabled(dto.getEnabled());

        if (dto.getPassword() != null && !dto.getPassword().isBlank()) {
            user.setPassword(passwordEncoder.encode(dto.getPassword()));
        }

        if (dto.getRoles() != null && !dto.getRoles().isEmpty()) {
            Set<Role> roles = dto.getRoles().stream()
                    .map(roleName ->
                            roleRepository.findByName(RoleType.valueOf(roleName))
                                    .orElseThrow(() ->
                                            new NotFoundException("Role not found: " + roleName))
                    )
                    .collect(Collectors.toSet());
            user.setRoles(roles);
        }
    }

    private Role getRole(RoleType roleType) {
        return roleRepository.findByName(roleType)
                .orElseThrow(() -> new NotFoundException(roleType + " not found"));
    }
}
