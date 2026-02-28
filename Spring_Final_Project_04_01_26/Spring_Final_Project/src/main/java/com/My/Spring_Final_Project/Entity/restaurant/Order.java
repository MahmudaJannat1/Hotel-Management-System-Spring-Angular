package com.My.Spring_Final_Project.Entity.restaurant;
import com.My.Spring_Final_Project.Entity.common.SoftDeleteEntity;
import com.My.Spring_Final_Project.Enum.Restaurant.OrderStatus;
import jakarta.persistence.*;

import java.time.LocalDateTime;


@Entity
@Table(name = "orders")
public class Order extends SoftDeleteEntity {
    private LocalDateTime orderDate;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;  // PENDING, COMPLETED, CANCELLED
}
