package com.My.Spring_Final_Project.Entity.corporate;
import com.My.Spring_Final_Project.Entity.common.SoftDeleteEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "corporate_contracts")
public class CorporateContract extends SoftDeleteEntity {
    @ManyToOne
    @JoinColumn(name = "client_id")
    private CorporateClient client;

    private LocalDateTime startDate;
    private LocalDateTime endDate;

    @OneToMany(mappedBy = "contract")
    private List<CorporateDiscount> discounts;

    @OneToMany(mappedBy = "contract")
    private List<CorporateInvoice> invoices;
}

