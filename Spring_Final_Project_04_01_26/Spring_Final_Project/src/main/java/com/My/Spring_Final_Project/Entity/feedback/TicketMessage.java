package com.My.Spring_Final_Project.Entity.feedback;
import com.My.Spring_Final_Project.Entity.common.SoftDeleteEntity;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "ticket_messages")
public class TicketMessage extends SoftDeleteEntity {
    @ManyToOne
    private SupportTicket ticket;

    private String message;
    private LocalDateTime createdAt;
}
