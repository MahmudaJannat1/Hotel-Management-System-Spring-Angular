package com.My.Spring_Final_Project.Entity.feedback;

import com.My.Spring_Final_Project.Entity.common.SoftDeleteEntity;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "feedbacks")
public class Feedback extends SoftDeleteEntity {
    private String userName;
    private String message;
    private LocalDateTime createdAt;
}
