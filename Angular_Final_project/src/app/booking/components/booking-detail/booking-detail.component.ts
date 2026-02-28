import { Component, OnInit } from '@angular/core';
import { BookingResponse } from '../../models/booking-response';
import { ActivatedRoute } from '@angular/router';
import { BookingService } from '../../services/booking.service';

@Component({
  selector: 'app-booking-detail',
  templateUrl: './booking-detail.component.html',
  styleUrls: ['./booking-detail.component.scss']
})
export class BookingDetailComponent implements OnInit {

  booking!: BookingResponse;
  bookingCode!: string;

  constructor(
    private route: ActivatedRoute,
    private bookingService: BookingService
  ) { }

  ngOnInit(): void {
    this.bookingCode = this.route.snapshot.params['code'];
    this.bookingService.getByCode(this.bookingCode).subscribe({
      next: res => this.booking = res,
      error: err => console.error(err)
    });
  }
}

