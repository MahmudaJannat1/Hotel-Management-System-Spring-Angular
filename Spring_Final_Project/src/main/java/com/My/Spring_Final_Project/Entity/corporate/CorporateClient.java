package com.My.Spring_Final_Project.Entity.corporate;

import com.My.Spring_Final_Project.Entity.common.SoftDeleteEntity;
import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Entity
@Table(name = "corporate_clients")
public class CorporateClient extends SoftDeleteEntity {
    private String name;
    private String email;
    private String phone;
    private String address;

    @OneToMany(mappedBy = "client")
    private List<CorporateContract> contracts;
}