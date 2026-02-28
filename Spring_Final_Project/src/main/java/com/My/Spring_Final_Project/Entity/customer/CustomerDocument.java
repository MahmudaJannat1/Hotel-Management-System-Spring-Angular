package com.My.Spring_Final_Project.Entity.customer;

import com.My.Spring_Final_Project.Entity.common.SoftDeleteEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "customer_documents")
@Getter
@Setter
public class CustomerDocument extends SoftDeleteEntity {
    private String documentType;
    private String documentUrl;

    @ManyToOne(fetch = FetchType.LAZY)
    private Customer customer;
}
