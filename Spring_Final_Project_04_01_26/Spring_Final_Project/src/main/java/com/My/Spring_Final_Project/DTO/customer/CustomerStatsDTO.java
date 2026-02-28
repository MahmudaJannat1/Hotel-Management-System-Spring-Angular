package com.My.Spring_Final_Project.DTO.customer;

import lombok.Data;

@Data
public class CustomerStatsDTO {
    private Long totalCustomers;
    private Long activeCustomers;
    private Long inactiveCustomers; // optional
}
