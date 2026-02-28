package com.My.Spring_Final_Project.Entity.auth;

import com.My.Spring_Final_Project.Entity.common.SoftDeleteEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "password_reset_tokens")
@Getter
@Setter
public class PasswordResetToken extends SoftDeleteEntity {
    private String token;
    private LocalDateTime expiryDate;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;
}