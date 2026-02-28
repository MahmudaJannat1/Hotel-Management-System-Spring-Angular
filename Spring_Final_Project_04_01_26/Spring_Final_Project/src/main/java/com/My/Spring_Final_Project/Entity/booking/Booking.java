package com.My.Spring_Final_Project.Entity.booking;

import com.My.Spring_Final_Project.Entity.auth.User;
import com.My.Spring_Final_Project.Entity.customer.Customer;
import com.My.Spring_Final_Project.Entity.common.SoftDeleteEntity;
import com.My.Spring_Final_Project.Entity.payment.Invoice;
import com.My.Spring_Final_Project.Entity.room.Room;
import com.My.Spring_Final_Project.Entity.room.RoomType;
import com.My.Spring_Final_Project.Enum.booking.BookingStatus;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "bookings")
@Getter
@Setter
public class Booking extends SoftDeleteEntity {

    @Column(nullable = false, unique = true)
    private String bookingCode;

    @Enumerated(EnumType.STRING)
    private BookingStatus status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    private LocalDate checkInDate;
    private LocalDate checkOutDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "room_id")
    private Room room;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @OneToMany(
            mappedBy = "booking",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.LAZY

    )
    private List<BookingItem> items = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "created_by")
    private User createdBy;

    @Column(name = "total_amount")
    private BigDecimal totalAmount;


    @OneToOne(mappedBy = "booking", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Invoice invoice;


    // ✅ RoomType relationship
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "room_type_id") // foreign key pointing to room_types table
    private RoomType roomType;


    @Column(name = "adults")
    private Integer adults;         // new

    @Column(name = "children")
    private Integer children;       // new
}

