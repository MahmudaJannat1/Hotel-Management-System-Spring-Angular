package com.My.Spring_Final_Project.DTO.booking;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookingItemRequestDTO {

    private String itemName;     // dropdown selected (DB match)
    private Integer quantity;
    private String description;  // optional note
    private String category;
    private BigDecimal price;

}
