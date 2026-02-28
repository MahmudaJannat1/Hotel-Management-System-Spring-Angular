package com.My.Spring_Final_Project.Entity.feedback;
import com.My.Spring_Final_Project.Entity.common.SoftDeleteEntity;
import com.My.Spring_Final_Project.Enum.Feedback.TicketStatus;
import jakarta.persistence.*;

@Entity
@Table(name = "support_tickets")
public class SupportTicket extends SoftDeleteEntity {
    private String subject;
    private String customerName;

    @Enumerated(EnumType.STRING)
    private TicketStatus status; // OPEN, IN_PROGRESS, CLOSED
}
