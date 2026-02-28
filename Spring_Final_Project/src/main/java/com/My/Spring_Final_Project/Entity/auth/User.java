package com.My.Spring_Final_Project.Entity.auth;

import com.My.Spring_Final_Project.Entity.common.SoftDeleteEntity;
import jakarta.persistence.*;
import lombok.*;
import java.util.Set;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User extends SoftDeleteEntity {

    @Column(nullable = false, unique = true)
    private String userName;

    private String userFirstName;
    private String userLastName;

    private String password;

    @Column(nullable = false, unique = true)
    private String email;


    // Initialize all Boolean fields with default values
    @Column(nullable = false)
    private Boolean enabled = true;
    private Boolean credentialsNonExpired = true;
    private Boolean accountNonExpired = true;
    private Boolean accountNonLocked = true;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_roles",
            joinColumns = @JoinColumn(name = "user_name"),
            inverseJoinColumns = @JoinColumn(name = "role_name")
    )
    private Set<Role> roles;


}
