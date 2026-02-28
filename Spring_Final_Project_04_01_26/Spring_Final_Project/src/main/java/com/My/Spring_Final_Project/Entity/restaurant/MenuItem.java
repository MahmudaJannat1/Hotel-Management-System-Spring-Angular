package com.My.Spring_Final_Project.Entity.restaurant;
import com.My.Spring_Final_Project.Entity.common.SoftDeleteEntity;
import jakarta.persistence.*;



@Entity
@Table(name = "menu_items")
public class MenuItem extends SoftDeleteEntity {
    private String name;
    private Double price;

    @ManyToOne
    private MenuCategory category;
}