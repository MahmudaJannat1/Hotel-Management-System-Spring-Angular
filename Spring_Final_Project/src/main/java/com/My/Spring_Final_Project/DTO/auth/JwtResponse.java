package com.My.Spring_Final_Project.DTO.auth;

import com.My.Spring_Final_Project.Entity.auth.User;
import java.util.Set;

public record JwtResponse(
        String jwtToken,
        User user,
        Set<String> roles

) {}
