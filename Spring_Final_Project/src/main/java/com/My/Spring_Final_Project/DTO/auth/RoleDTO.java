package com.My.Spring_Final_Project.DTO.auth;

import com.My.Spring_Final_Project.Enum.Auth.RoleType;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RoleDTO {

    private RoleType roleName;      // maps to Role.name
    private String roleDescription; // maps to Role.roleDescription
}
