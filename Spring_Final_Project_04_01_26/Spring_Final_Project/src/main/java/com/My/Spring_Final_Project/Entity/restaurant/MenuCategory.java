package com.My.Spring_Final_Project.Entity.restaurant;

import com.My.Spring_Final_Project.Entity.common.SoftDeleteEntity;
import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "menu_categories")
public class MenuCategory extends SoftDeleteEntity {
    private String name;

    @OneToMany(mappedBy = "category")
    private List<MenuItem> items;
}
