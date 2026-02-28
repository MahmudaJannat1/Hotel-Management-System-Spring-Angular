package com.My.Spring_Final_Project.Entity.logs;
import com.My.Spring_Final_Project.Entity.common.SoftDeleteEntity;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "device_status")
public class DeviceStatus extends SoftDeleteEntity {
    private String deviceName;
    private Boolean isOnline;
    private LocalDateTime checkedAt;
}