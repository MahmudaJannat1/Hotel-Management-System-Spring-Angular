package com.My.Spring_Final_Project.Entity.logs;
import com.My.Spring_Final_Project.Entity.common.SoftDeleteEntity;
import jakarta.persistence.*;

import java.time.LocalDateTime;


@Entity
@Table(name = "audit_logs")
public class AuditLog extends SoftDeleteEntity {
    private String action;
    private String entityName;
    private String performedBy;
    private LocalDateTime performedAt;
}
