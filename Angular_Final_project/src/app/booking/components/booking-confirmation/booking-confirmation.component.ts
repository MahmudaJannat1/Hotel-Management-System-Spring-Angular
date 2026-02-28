import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { BookingService } from '../../services/booking.service';
import { BookingResponse } from '../../models/booking-response';

@Component({
  selector: 'app-booking-confirmation',
  templateUrl: './booking-confirmation.component.html',
  styleUrls: ['./booking-confirmation.component.scss']
})
export class BookingConfirmationComponent implements OnInit {

  booking!: BookingResponse;
  bookingCode!: string;
  loading = true;

  constructor(
    private route: ActivatedRoute,
    private bookingService: BookingService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.bookingCode = this.route.snapshot.paramMap.get('code')!;

    this.bookingService.getByCode(this.bookingCode).subscribe({
      next: res => {
        this.booking = res;
        this.loading = false;
      },
      error: () => {
        alert('Invalid booking code!');
        this.router.navigate(['/rooms']);
      }
    });
  }

  proceedToPayment(): void {
  this.router.navigate(
    ['/payment/new'],
    {
      queryParams: {
        bookingCode: this.booking.bookingCode
      }
    }
  );
}
}
