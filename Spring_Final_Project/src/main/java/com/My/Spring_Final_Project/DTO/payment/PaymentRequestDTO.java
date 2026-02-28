package com.My.Spring_Final_Project.DTO.payment;


import com.My.Spring_Final_Project.Enum.Event.PaymentMethod;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class PaymentRequestDTO {
    private Long bookingId;
    private Double amount;  // keep as Double
    private PaymentMethod method;
}

