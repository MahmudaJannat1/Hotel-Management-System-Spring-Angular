package com.My.Spring_Final_Project.Entity.hotel;

import com.My.Spring_Final_Project.Entity.common.SoftDeleteEntity;
import com.My.Spring_Final_Project.Entity.room.Room;
import jakarta.persistence.Entity;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "hotels")
@Getter
@Setter
public class Hotel extends SoftDeleteEntity {
    private String name;
    private String location;

    @OneToMany(mappedBy = "hotel")
    private List<HotelStaff> staff;

    @OneToMany(mappedBy = "hotel")
    private List<Room> rooms;

}