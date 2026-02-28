import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { PaymentService } from '../../services/payment.service';
import { PaymentResponse } from '../../models/payment-response.model';

@Component({
  selector: 'app-payment-confirmation',
  templateUrl: './payment-confirmation.component.html',
  styleUrls: ['./payment-confirmation.component.scss']
})
export class PaymentConfirmationComponent implements OnInit {

   paymentId!: number;
  payment?: PaymentResponse;
  loading = true;

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private paymentService: PaymentService
  ) {}

ngOnInit(): void {
  const idParam = this.route.snapshot.paramMap.get('id'); // <-- changed here

  if (!idParam) {
    alert('Invalid payment reference!');
    this.router.navigate(['/']);
    return;
  }

  this.paymentId = +idParam;
  this.loadPayment();
}


  loadPayment(): void {
    this.paymentService.get(this.paymentId).subscribe({
      next: res => {
        this.payment = res;
        this.loading = false;
      },
      error: err => {
        console.error(err);
        alert('Failed to load payment details');
        this.router.navigate(['/']);
      }
    });
  }

  // ✅ View Invoice button handler
viewInvoice(): void {
  if (!this.payment?.invoiceId) {
    alert('Invoice not available yet!');
    return;
  }
this.router.navigate(['/payment/history']);
}

goToInvoice(): void {
  if (!this.payment?.invoiceId) {
    alert('Invoice not available');
    return;
  }
  this.router.navigate(['/payment/invoice', this.payment.invoiceId]);
}



}
