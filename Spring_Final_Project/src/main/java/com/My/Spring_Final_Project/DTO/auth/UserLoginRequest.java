package com.My.Spring_Final_Project.DTO.auth;


public record UserLoginRequest(
        String username,
        String password) {}