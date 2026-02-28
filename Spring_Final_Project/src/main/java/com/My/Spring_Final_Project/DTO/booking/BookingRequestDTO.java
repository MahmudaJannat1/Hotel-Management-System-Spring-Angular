package com.My.Spring_Final_Project.DTO.booking;

import com.My.Spring_Final_Project.Enum.room.RoomType;
import lombok.*;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
@Data
public class BookingRequestDTO {
    Long roomId;
     Long customerId;
     LocalDate checkInDate;
     LocalDate checkOutDate;
     List<BookingItemRequestDTO> items;
    private Integer adults;   // নতুন ফিল্ড
    private Integer children; // নতুন ফিল্ড
    private String roomType;  // <-- entity type er jonne String use korbo


}
