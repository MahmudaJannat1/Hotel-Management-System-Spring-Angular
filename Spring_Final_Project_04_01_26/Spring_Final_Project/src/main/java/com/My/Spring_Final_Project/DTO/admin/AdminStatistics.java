package com.My.Spring_Final_Project.DTO.admin;

import com.My.Spring_Final_Project.Entity.auth.Role;
import java.util.List;


public record AdminStatistics(
        long totalUsers,
        long enabledUsers,
        List<Role> roles) {
}
