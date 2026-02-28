package com.My.Spring_Final_Project.Entity.room;

import com.My.Spring_Final_Project.Entity.common.SoftDeleteEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "room_types")
@Getter
@Setter
public class RoomType extends SoftDeleteEntity {

    private String name;
    private String description;
    private Double pricePerNight;
}
