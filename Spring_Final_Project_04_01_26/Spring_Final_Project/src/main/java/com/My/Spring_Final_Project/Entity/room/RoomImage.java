package com.My.Spring_Final_Project.Entity.room;

import com.My.Spring_Final_Project.Entity.common.SoftDeleteEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "room_images")
@Getter
@Setter
public class RoomImage extends SoftDeleteEntity {

    private String filename;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "room_id")
    private Room room;
}
