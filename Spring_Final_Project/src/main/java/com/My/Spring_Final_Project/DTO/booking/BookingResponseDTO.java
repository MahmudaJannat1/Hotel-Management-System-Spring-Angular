package com.My.Spring_Final_Project.DTO.booking;

import com.My.Spring_Final_Project.Enum.room.RoomType;
import lombok.*;

import java.util.List;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

    public class BookingResponseDTO {
        private Long id;            // ✅ ADD THIS
        private String bookingCode;
        private Long customerId;     // add this
        private String customerName;
        private Long roomId;         // add this
        private String roomNumber;
        private String checkInDate;
        private String checkOutDate;
        private String status;
        List<BookingItemResponseDTO> items;

    // ✅ new fields
    private Integer adults;
    private Integer children;
    private Integer numberOfNights;
    private String roomType;  // <-- entity type er jonne String use korbo
}
