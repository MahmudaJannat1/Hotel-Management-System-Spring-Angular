package com.My.Spring_Final_Project.restController;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
@RestController
@RequestMapping("/api/debug")
@CrossOrigin(origins = "*", maxAge = 3600)
public class DebugController {

    @GetMapping("/my-roles")
    @PreAuthorize("isAuthenticated()")
    public Map<String, Object> getMyRoles() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        Map<String, Object> debugInfo = new HashMap<>();
        debugInfo.put("username", auth.getName());
        debugInfo.put("authorities", auth.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList()));
        debugInfo.put("authenticated", auth.isAuthenticated());

        return debugInfo;
    }
}