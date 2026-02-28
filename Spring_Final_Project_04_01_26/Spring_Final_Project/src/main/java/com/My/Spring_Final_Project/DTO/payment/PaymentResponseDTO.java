package com.My.Spring_Final_Project.DTO.payment;


import com.My.Spring_Final_Project.Enum.Event.PaymentMethod;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class PaymentResponseDTO {
    private Long id;
    private Double amount; // keep as Double
    private PaymentMethod method;
    private LocalDateTime paymentDate;
    private Long bookingId;
    private Long invoiceId;       // add this
    private String invoiceNumber; // add this
    private String totalAmount;  // Invoice total amount as string


}

