package com.My.Spring_Final_Project.Service.customer;

import com.My.Spring_Final_Project.DTO.customer.CustomerRequestDTO;
import com.My.Spring_Final_Project.DTO.customer.CustomerResponseDTO;
import com.My.Spring_Final_Project.DTO.customer.CustomerStatsDTO;
import com.My.Spring_Final_Project.Entity.customer.Customer;
import com.My.Spring_Final_Project.Repository.customer.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    @Override
    public CustomerResponseDTO createCustomer(CustomerRequestDTO dto) {
        Customer customer = new Customer();
        customer.setFirstName(dto.getName()); // short: storing full name in firstName
        customer.setEmail(dto.getEmail());
        customer.setPhone(dto.getPhone());

        customerRepository.save(customer);
        return mapToDTO(customer);
    }

    @Override
    public CustomerResponseDTO getCustomer(Long id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer not found"));
        return mapToDTO(customer);
    }

    @Override
    public List<CustomerResponseDTO> getAllCustomers() {
        return customerRepository.findByIsActiveTrue()
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public CustomerResponseDTO updateCustomer(Long id, CustomerRequestDTO dto) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        if(dto.getName() != null) customer.setFirstName(dto.getName());
        if(dto.getEmail() != null) customer.setEmail(dto.getEmail());
        if(dto.getPhone() != null) customer.setPhone(dto.getPhone());

        customerRepository.save(customer);
        return mapToDTO(customer);
    }

    @Override
    public void deleteCustomer(Long id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer not found"));
        customer.setIsActive(false); // soft delete
        customerRepository.save(customer);
    }

    @Override
    public CustomerStatsDTO getCustomerStats() {
        CustomerStatsDTO stats = new CustomerStatsDTO();
        stats.setTotalCustomers(customerRepository.count());
        stats.setActiveCustomers(customerRepository.countByIsActiveTrue());
        stats.setInactiveCustomers(customerRepository.countByIsActiveFalse());
        return stats;
    }

    // Helper mapper
    private CustomerResponseDTO mapToDTO(Customer customer) {
        CustomerResponseDTO dto = new CustomerResponseDTO();
        dto.setId(customer.getId());
        dto.setName(customer.getFirstName());
        dto.setEmail(customer.getEmail());
        dto.setPhone(customer.getPhone());
        return dto;
    }
}
