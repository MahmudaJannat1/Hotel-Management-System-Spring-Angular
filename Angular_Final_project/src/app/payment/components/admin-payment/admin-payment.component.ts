import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AdminPaymentService } from '../../services/admin-payment.service';
import { PaymentStats } from '../../models/payment-stats.model';
import { PaymentResponse } from '../../models/payment-response.model';

@Component({
  selector: 'app-admin-payment',
  templateUrl: './admin-payment.component.html',
  styleUrls: ['./admin-payment.component.scss']
})
export class AdminPaymentComponent implements OnInit {

  stats!: PaymentStats;
  payments: PaymentResponse[] = [];
  loading = true;

  constructor(
    private paymentService: AdminPaymentService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.loadStats();
    this.loadPayments();
  }

  loadStats() {
    this.paymentService.getPaymentStats().subscribe(res => {
      this.stats = res;
    });
  }

  loadPayments() {
    this.paymentService.getAllPayments().subscribe(res => {
      this.payments = res;
      this.loading = false;
    });
  }

  cancelPayment(id: number) {
    if(confirm('Are you sure you want to cancel this payment?')) {
      this.paymentService.cancelPayment(id).subscribe(() => {
        this.loadPayments(); // Refresh table
        this.loadStats(); // Refresh stats
      });
    }
  }
viewInvoice(invoiceId: number | null) {

  console.log('Invoice ID:', invoiceId);

  if (!invoiceId) {
    alert('Invoice not generated yet');
    return;
  }

  this.router.navigate(['/payment/invoice', invoiceId]);
}



  createPayment() {
    this.router.navigate(['/payment/new']);
  }
  goToStats() {
  this.router.navigate(['/payment/admin/stats']); 
}



}
