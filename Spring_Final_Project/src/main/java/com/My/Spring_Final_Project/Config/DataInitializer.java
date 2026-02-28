package com.My.Spring_Final_Project.Config;

import com.My.Spring_Final_Project.Service.Auth.UserService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataInitializer {

    @Autowired
    private UserService userService;

    @PostConstruct
    public void init() {
        userService.initRoleAndUser();
    }
}
