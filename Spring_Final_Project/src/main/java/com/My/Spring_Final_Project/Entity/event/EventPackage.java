package com.My.Spring_Final_Project.Entity.event;
import com.My.Spring_Final_Project.Entity.common.SoftDeleteEntity;
import jakarta.persistence.*;

@Entity
@Table(name = "event_packages")
public class EventPackage extends SoftDeleteEntity {
    private String name;
    private Double price;
}