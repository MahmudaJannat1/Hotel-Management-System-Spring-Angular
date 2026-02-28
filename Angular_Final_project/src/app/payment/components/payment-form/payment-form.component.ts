import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { PaymentService } from '../../services/payment.service';
import { PaymentRequest as PaymentRequestModel } from '../../models/payment-request.model';
import { PaymentResponse } from '../../models/payment-response.model';
import { BookingService } from 'src/app/booking/services/booking.service';

@Component({
  selector: 'app-payment-form',
  templateUrl: './payment-form.component.html',
  styleUrls: ['./payment-form.component.scss']
})
export class PaymentFormComponent implements OnInit {

  paymentForm!: FormGroup;
  bookingId!: number;
  bookingCode!: string;

  constructor(
    private fb: FormBuilder,
    private paymentService: PaymentService,
    private bookingService: BookingService,
    private route: ActivatedRoute,
    private router: Router
  ) {}

  ngOnInit(): void {
    const code = this.route.snapshot.queryParamMap.get('bookingCode');

    if (!code) {
      alert('Booking not specified!');
      this.router.navigate(['/']);
      return;
    }

    this.bookingCode = code;

    // 🔑 bookingCode → booking → bookingId
    this.bookingService.getByCode(this.bookingCode).subscribe({
      next: (booking: any) => {
        this.bookingId = booking.id; // backend DTO has id internally
      },
      error: () => {
        alert('Invalid booking!');
        this.router.navigate(['/']);
      }
    });

    // 🔹 Payment form
    this.paymentForm = this.fb.group({
      amount: [null, [Validators.required, Validators.min(1)]],
      method: ['CASH', Validators.required],

    });
  }

// submitPayment() {
//   if (!this.bookingId) {
//     alert('Booking is still loading. Please wait.');
//     return;
//   }

//   if (this.paymentForm.invalid) return;

//   const dto: PaymentRequestModel = {
//     bookingId: this.bookingId,
//     amount: this.paymentForm.value.amount,
//     method: this.paymentForm.value.method
//   };

//   this.paymentService.create(dto).subscribe({
//     next: (res: PaymentResponse) => {
//       // ✅ Redirect immediately
//       this.router.navigate(['/payment/confirmation', this.bookingId]);
//       // Email already sent from backend (temporary fix)
//     },
//     error: (err) => {
//       const msg = err?.error?.message || 'Payment failed!';
//       alert(msg);
//     }
//   });
// }





submitPayment() {
  if (!this.bookingId) {
    alert('Booking is still loading. Please wait.');
    return;
  }

  if (this.paymentForm.invalid) return;

  const dto: PaymentRequestModel = {
    bookingId: this.bookingId,
    amount: this.paymentForm.value.amount,
    method: this.paymentForm.value.method
  };

this.paymentService.create(dto).subscribe({
  next: (res: PaymentResponse) => {
    console.log('Payment response:', res); // 🔹 Check this
    if (!res.id) {
      alert('Payment ID not found. Please try again.');
      return;
    }
    this.router.navigate(['/payment/confirmation', res.id]);
  },
  error: (err) => {
    const msg = err?.error?.message || 'Payment failed!';
    alert(msg);
  }
});
}
}