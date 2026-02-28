package com.My.Spring_Final_Project.Entity.room;

import com.My.Spring_Final_Project.Entity.common.SoftDeleteEntity;
import com.My.Spring_Final_Project.Entity.hotel.Hotel;
import com.My.Spring_Final_Project.Enum.room.RoomStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;
@Entity
@Table(name = "rooms")
@Getter
@Setter
public class Room extends SoftDeleteEntity {

    private String name; // <-- add this

    private String roomNumber;
    private String floor;

    @Enumerated(EnumType.STRING)
    private RoomStatus status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "room_type_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private RoomType roomType;

    // 🔥 IMPORTANT: keep LAZY (default) + orphanRemoval
    @OneToMany(
            mappedBy = "room",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.LAZY
    )
    @JsonIgnore
    private List<RoomImage> images;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hotel_id")
    @JsonIgnore
    private Hotel hotel;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "room_amenities_mapping",
            joinColumns = @JoinColumn(name = "room_id"),
            inverseJoinColumns = @JoinColumn(name = "amenity_id")
    )
    @JsonIgnore
    private List<RoomAmenity> amenities;

    private BigDecimal pricePerNight;

    private Integer sizeSqm;
    private Integer maxGuests;
    private Integer adults;

    @Column(length = 2000)
    private String shortDescription;
}
