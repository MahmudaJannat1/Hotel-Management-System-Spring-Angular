package com.My.Spring_Final_Project.Entity.logs;

import com.My.Spring_Final_Project.Entity.common.SoftDeleteEntity;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "access_logs")
public class AccessLog extends SoftDeleteEntity {
    private String userName;
    private String resource;
    private LocalDateTime accessTime;
}