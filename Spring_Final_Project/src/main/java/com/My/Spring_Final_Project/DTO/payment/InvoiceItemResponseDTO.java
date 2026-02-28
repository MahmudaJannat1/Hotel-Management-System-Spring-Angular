package com.My.Spring_Final_Project.DTO.payment;

import lombok.Data;
import java.math.BigDecimal;

@Data
public class InvoiceItemResponseDTO {
    private String description;
    private Integer quantity;
    private BigDecimal amount; // ✅ changed from Double to BigDecimal
}
