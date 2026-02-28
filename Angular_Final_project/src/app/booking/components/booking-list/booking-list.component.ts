    // booking-list.component.ts
import { Component, OnInit } from '@angular/core';
import { BookingService } from '../../services/booking.service';
import { AdminBookingService } from '../../services/admin-booking.service';
import { ActivatedRoute } from '@angular/router';
import { BookingResponse } from '../../models/booking-response';

@Component({
  selector: 'app-booking-list',
  templateUrl: './booking-list.component.html',
  styleUrls: ['./booking-list.component.scss']
})
export class BookingListComponent implements OnInit {

  bookings: BookingResponse[] = [];
  isAdminView = false;

  constructor(
    private bookingService: BookingService,
    private adminBookingService: AdminBookingService,
    private route: ActivatedRoute
  ) {}

  ngOnInit(): void {
this.isAdminView = this.route.snapshot.url[0]?.path === 'admin';
    if (this.isAdminView) {
      this.adminBookingService.getAll().subscribe(data => this.bookings = data);
    } else {
      this.bookingService.getAll().subscribe(data => this.bookings = data);
    }
  }

  cancelBooking(code: string) {
  if (!confirm('Are you sure you want to cancel this booking?')) return;

  this.adminBookingService.cancel(code).subscribe(() => {
    // remove cancelled booking from the list
    this.bookings = this.bookings.filter(b => b.bookingCode !== code);
  });
}
}
