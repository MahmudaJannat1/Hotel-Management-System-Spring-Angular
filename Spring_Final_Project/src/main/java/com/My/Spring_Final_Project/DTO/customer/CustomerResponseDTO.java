package com.My.Spring_Final_Project.DTO.customer;

import lombok.Data;

@Data
public class CustomerResponseDTO {
    private Long id;
    private String name;
    private String email;
    private String phone;
}

