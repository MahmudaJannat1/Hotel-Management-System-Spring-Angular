package com.My.Spring_Final_Project.DTO.auth;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserRegisterRequest {

    private String username;
    private String password;
    private String email;
    private String firstName;
    private String lastName;
}
