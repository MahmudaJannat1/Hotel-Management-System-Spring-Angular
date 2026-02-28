// payment-success.component.ts
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { PaymentService } from '../../services/payment.service';

@Component({
  selector: 'app-payment-success',
  templateUrl: './payment-success.component.html',
  styleUrls: ['./payment-success.component.scss']
})
export class PaymentSuccessComponent implements OnInit {
  ngOnInit(): void {
    throw new Error('Method not implemented.');
  }
  // paymentId: number = 0;
  // paymentDetails: any;

  // constructor(
  //   private route: ActivatedRoute,
  //   private router: Router,
  //   private paymentService: PaymentService
  // ) {}

  // ngOnInit(): void {
  //   this.paymentId = +this.route.snapshot.params['id'];
  //   this.loadPaymentDetails();
  // }

  // loadPaymentDetails() {
  //   this.paymentService.getPayment(this.paymentId).subscribe({
  //     next: (data) => this.paymentDetails = data,
  //     error: (err) => console.error(err)
  //   });
  // }
}