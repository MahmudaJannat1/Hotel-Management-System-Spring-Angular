package com.My.Spring_Final_Project.Entity.booking;

import com.My.Spring_Final_Project.Entity.common.SoftDeleteEntity;
import com.My.Spring_Final_Project.Entity.room.Room;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "booking_items")
@Getter
@Setter
public class BookingItem extends SoftDeleteEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "booking_id")
    private Booking booking;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "room_id")
    private Room room;

    @Column(precision = 10, scale = 2)
    private BigDecimal price;
    private Integer quantity;

    @Column(name = "item_name")
    private String itemName;

    @Column(name = "description")
    private String description;    // optional note

    @Column(name = "category")
    private String category;       // e.g., SERVICE, FOOD, EXTRA
}
