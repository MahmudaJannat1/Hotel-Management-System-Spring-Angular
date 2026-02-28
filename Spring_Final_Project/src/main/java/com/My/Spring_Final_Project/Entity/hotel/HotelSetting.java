package com.My.Spring_Final_Project.Entity.hotel;

import com.My.Spring_Final_Project.Entity.common.SoftDeleteEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "hotel_settings")
@Getter
@Setter
public class HotelSetting extends SoftDeleteEntity {
    private String settingName;
    private String settingValue;

    @ManyToOne(fetch = FetchType.LAZY)
    private Hotel hotel;
}