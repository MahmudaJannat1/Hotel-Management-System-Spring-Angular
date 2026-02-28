package com.My.Spring_Final_Project.Entity.event;

import com.My.Spring_Final_Project.Entity.common.SoftDeleteEntity;
import jakarta.persistence.*;

@Entity
@Table(name = "event_halls")
public class EventHall extends SoftDeleteEntity {
    private String name;
    private String location;
    private Integer capacity;
}