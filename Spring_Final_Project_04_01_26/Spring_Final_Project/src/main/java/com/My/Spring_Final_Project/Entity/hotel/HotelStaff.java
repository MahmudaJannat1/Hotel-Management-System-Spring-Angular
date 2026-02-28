package com.My.Spring_Final_Project.Entity.hotel;

import com.My.Spring_Final_Project.Entity.common.SoftDeleteEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "hotel_staff")
@Getter
@Setter
public class HotelStaff extends SoftDeleteEntity {
    private String name;
    private String position;

    @ManyToOne(fetch = FetchType.LAZY)
    private Hotel hotel;
}
