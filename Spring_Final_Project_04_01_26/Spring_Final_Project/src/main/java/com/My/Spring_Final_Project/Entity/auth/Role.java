package com.My.Spring_Final_Project.Entity.auth;

import com.My.Spring_Final_Project.Enum.Auth.RoleType;
import com.My.Spring_Final_Project.Entity.common.SoftDeleteEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "roles")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Role extends SoftDeleteEntity {

    @Column
    private String roleDescription;

    @Enumerated(EnumType.STRING)
    private RoleType name;
}
