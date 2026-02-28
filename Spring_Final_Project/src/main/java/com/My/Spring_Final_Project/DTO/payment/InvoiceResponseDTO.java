package com.My.Spring_Final_Project.DTO.payment;

import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class InvoiceResponseDTO {
    private Long id;
    private String invoiceNumber;
    private LocalDateTime invoiceDate;
    private Long bookingId;
    private BigDecimal totalAmount; // ✅ changed from Double to BigDecimal
    private List<InvoiceItemResponseDTO> items;
}
