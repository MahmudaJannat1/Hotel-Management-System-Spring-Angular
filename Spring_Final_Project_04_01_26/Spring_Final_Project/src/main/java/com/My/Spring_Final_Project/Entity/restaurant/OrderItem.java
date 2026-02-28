package com.My.Spring_Final_Project.Entity.restaurant;
import com.My.Spring_Final_Project.Entity.common.SoftDeleteEntity;
import jakarta.persistence.*;


@Entity
@Table(name = "order_items")
public class OrderItem extends SoftDeleteEntity {
    @ManyToOne
    private Order order;

    @ManyToOne
    private MenuItem menuItem;

    private Integer quantity;
}
