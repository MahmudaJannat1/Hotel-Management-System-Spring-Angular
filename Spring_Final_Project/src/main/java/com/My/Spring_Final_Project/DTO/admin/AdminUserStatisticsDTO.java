package com.My.Spring_Final_Project.DTO.admin;

import com.My.Spring_Final_Project.Entity.auth.Role;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Data
public class AdminUserStatisticsDTO{
    long totalUsers;
    long enabledUsers;
    List<Role> roles;
}
