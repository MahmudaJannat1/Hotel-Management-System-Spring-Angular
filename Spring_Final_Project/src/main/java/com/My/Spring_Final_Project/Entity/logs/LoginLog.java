package com.My.Spring_Final_Project.Entity.logs;
import com.My.Spring_Final_Project.Entity.common.SoftDeleteEntity;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "login_logs")
public class LoginLog extends SoftDeleteEntity {
    private String userName;
    private LocalDateTime loginTime;
    private String ipAddress;
}
