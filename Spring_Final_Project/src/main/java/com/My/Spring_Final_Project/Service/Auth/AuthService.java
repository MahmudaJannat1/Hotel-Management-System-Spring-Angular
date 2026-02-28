package com.My.Spring_Final_Project.Service.Auth;

import com.My.Spring_Final_Project.DTO.auth.JwtResponse;
import com.My.Spring_Final_Project.DTO.auth.UserLoginRequest;
import com.My.Spring_Final_Project.DTO.auth.UserRegisterRequest;
import com.My.Spring_Final_Project.Entity.auth.Role;
import com.My.Spring_Final_Project.Entity.auth.User;
import com.My.Spring_Final_Project.Enum.Auth.RoleType;
import com.My.Spring_Final_Project.Repository.auth.RoleRepository;
import com.My.Spring_Final_Project.Repository.auth.UserRepository;
import com.My.Spring_Final_Project.Security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
public class AuthService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // ================= LOGIN =================
    public JwtResponse login(UserLoginRequest userLoginRequest) {

        // 1️⃣ Authenticate username & password
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        userLoginRequest.username(),
                        userLoginRequest.password()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        // 2️⃣ Load USER ENTITY from DB (IMPORTANT)
        User user = userRepository.findByUserName(userLoginRequest.username())
                .orElseThrow(() -> new RuntimeException("User not found"));

        // 3️⃣ Generate JWT WITH ROLES
        String token = jwtUtil.generateTokenFromUsername(user);

        // 4️⃣ Extract roles (optional, for frontend)
        Set<String> roles = user.getRoles().stream()
                .map(role -> role.getName().name()) // ROLE_ADMIN
                .collect(Collectors.toSet());

        // 5️⃣ Return response
        return new JwtResponse(token, user, roles);
    }


    // ================= REGISTER =================
    public String register(UserRegisterRequest request) {
        validateRegistration(request);

        User user = createUserFromRequest(request);
        assignDefaultRole(user);

        userRepository.save(user);
        return "User registered successfully";
    }

    // ================= INITIALIZE ROLES =================
    public String initializeRoles() {
        RoleType[] defaultRoles = {
                RoleType.ROLE_USER,
                RoleType.ROLE_ADMIN,
                RoleType.ROLE_MODERATOR
        };

        for (RoleType roleType : defaultRoles) {
            roleRepository.findByName(roleType)
                    .orElseGet(() -> {
                        Role role = new Role();
                        role.setName(roleType);
                        role.setRoleDescription(roleType.name() + " role");
                        role.getCreatedAt();
                        role.getUpdatedAt();
                        return roleRepository.save(role);
                    });
        }
        return "Roles initialized successfully";
    }

    // ================= PRIVATE HELPERS =================
    private void validateRegistration(UserRegisterRequest request) {

        if (userRepository.existsByUserNameIgnoreCase(request.getUsername())) {
            throw new IllegalArgumentException("Username already exists");
        }

        if (userRepository.existsByEmailIgnoreCase(request.getEmail())) {
            throw new IllegalArgumentException("Email already exists");
        }
    }

    private User createUserFromRequest(UserRegisterRequest request) {

        User user = new User();
        user.setUserName(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setEmail(request.getEmail());
        user.setUserFirstName(request.getFirstName());
        user.setUserLastName(request.getLastName());

        // Default security flags
        user.setEnabled(true);
        user.setAccountNonExpired(true);
        user.setCredentialsNonExpired(true);
        user.setAccountNonLocked(true);

        return user;
    }

    private void assignDefaultRole(User user) {
        Set<Role> roles = new HashSet<>();

        Role userRole = roleRepository.findByName(RoleType.ROLE_USER)
                .orElseThrow(() -> new RuntimeException("Error: ROLE_USER not found"));

        roles.add(userRole);
        user.setRoles(roles);
    }
}
