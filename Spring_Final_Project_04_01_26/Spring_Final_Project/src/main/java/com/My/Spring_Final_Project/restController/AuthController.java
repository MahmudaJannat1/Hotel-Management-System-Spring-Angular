package com.My.Spring_Final_Project.restController;

import com.My.Spring_Final_Project.DTO.auth.JwtResponse;
import com.My.Spring_Final_Project.DTO.auth.UserLoginRequest;
import com.My.Spring_Final_Project.DTO.auth.UserRegisterRequest;
import com.My.Spring_Final_Project.Service.Auth.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*", maxAge = 3600)
public class AuthController {

    @Autowired
    private AuthService authService;

    // ================= LOGIN =================
    @PostMapping("/signin")
    public ResponseEntity<?> login(@RequestBody UserLoginRequest request) {
        try {
            JwtResponse response = authService.login(request);
            return ResponseEntity.ok(response);
        } catch (BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("Invalid username or password");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Login failed: " + e.getMessage());
        }
    }

    // ================= REGISTER =================
    @PostMapping("/signup")
    public ResponseEntity<?> register(@RequestBody UserRegisterRequest  request) {
        try {
            String result = authService.register(request);
            return ResponseEntity.ok(result);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Registration failed: " + e.getMessage());
        }
    }

    // ================= INIT ROLES (DEV ONLY) =================
    @PostMapping("/init-roles")
    public ResponseEntity<?> initRoles() {
        try {
            String result = authService.initializeRoles();
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to initialize roles: " + e.getMessage());
        }
    }
}
