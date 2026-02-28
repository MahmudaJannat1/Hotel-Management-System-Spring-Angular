import { Component, OnInit } from '@angular/core';
import { AdminBookingService } from '../../services/admin-booking.service';
import { BookingStats } from '../../models/booking-stats';
import { ChartData, ChartType } from 'chart.js';

@Component({
  selector: 'app-booking-stats',
  templateUrl: './booking-stats.component.html',
  styleUrls: ['./booking-stats.component.scss']
})
export class BookingStatsComponent implements OnInit {

  stats!: BookingStats;

  chartType: ChartType = 'bar';

  // ✅ IMPORTANT: ChartData type
  chartData: ChartData<'bar'> = {
    labels: ['Pending', 'Confirmed', 'Cancelled', 'Completed'],
    datasets: [
      {
        label: 'Bookings',
        data: [0, 0, 0, 0]
      }
    ]
  };

  

  constructor(private adminBookingService: AdminBookingService) {}

  ngOnInit(): void {
    this.adminBookingService.getStats().subscribe(res => {
      this.stats = res;

      this.chartData.datasets[0].data = [
        res.pendingBookings,
        res.confirmedBookings,
        res.cancelledBookings,
        res.completedBookings
      ];
    });
  }
}
