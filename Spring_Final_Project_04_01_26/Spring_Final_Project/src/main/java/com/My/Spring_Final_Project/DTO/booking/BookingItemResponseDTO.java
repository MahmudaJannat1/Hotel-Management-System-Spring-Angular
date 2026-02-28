package com.My.Spring_Final_Project.DTO.booking;

import lombok.*;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookingItemResponseDTO {
    private String itemName;  // String instead of enum
    private Integer quantity;
    private BigDecimal price;
    private String description;   // optional notes
    private String category;      // optional

}



