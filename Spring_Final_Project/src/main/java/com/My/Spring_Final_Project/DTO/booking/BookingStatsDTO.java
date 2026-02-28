package com.My.Spring_Final_Project.DTO.booking;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class BookingStatsDTO {

    private long totalBookings;
    private long pendingBookings;
    private long confirmedBookings;
    private long cancelledBookings;
    private long completedBookings;

}
