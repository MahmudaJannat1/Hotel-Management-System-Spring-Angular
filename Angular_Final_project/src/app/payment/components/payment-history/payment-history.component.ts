import { Component, OnInit } from '@angular/core';
import { PaymentService } from '../../services/payment.service';
import { PaymentResponse } from '../../models/payment-response.model';

@Component({
  selector: 'app-payment-history',
  templateUrl: './payment-history.component.html',
  styleUrls: ['./payment-history.component.scss']
})
export class PaymentHistoryComponent implements OnInit {
getMethodIcon(arg0: string) {
throw new Error('Method not implemented.');
}

  payments: PaymentResponse[] = [];
  loading = true;

  constructor(private paymentService: PaymentService) {}

  ngOnInit(): void {
    this.loadPayments();
  }

 loadPayments(): void {
  this.paymentService.getMyPayments().subscribe({
    next: res => {
      this.payments = res.map(p => ({
        ...p,
        amountNumber: Number(p.totalAmount) // convert string to number
      }));
      this.loading = false;
    },
    error: () => {
      alert('Failed to load payment history');
      this.loading = false;
    }
  });
}





}
