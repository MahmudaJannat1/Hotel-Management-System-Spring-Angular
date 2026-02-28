package com.My.Spring_Final_Project.Service.customer;

import com.My.Spring_Final_Project.DTO.customer.CustomerRequestDTO;
import com.My.Spring_Final_Project.DTO.customer.CustomerResponseDTO;
import com.My.Spring_Final_Project.DTO.customer.CustomerStatsDTO;

import java.util.List;

public interface CustomerService {

    CustomerResponseDTO createCustomer(CustomerRequestDTO dto);
    CustomerResponseDTO getCustomer(Long id);
    List<CustomerResponseDTO> getAllCustomers();
    CustomerResponseDTO updateCustomer(Long id, CustomerRequestDTO dto);
    void deleteCustomer(Long id); // soft delete
    CustomerStatsDTO getCustomerStats();

}
