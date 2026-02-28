package com.My.Spring_Final_Project.Entity.customer;

import com.My.Spring_Final_Project.Entity.common.SoftDeleteEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "customers")
@Getter
@Setter
public class Customer extends SoftDeleteEntity {
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
}

