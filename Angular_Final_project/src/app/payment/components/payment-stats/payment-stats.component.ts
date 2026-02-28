import { Component, OnInit } from '@angular/core';
import { PaymentService } from '../../services/payment.service';
import { PaymentStats } from '../../models/payment-stats.model';
import { AdminPaymentService } from '../../services/admin-payment.service';

@Component({
  selector: 'app-payment-stats',
  templateUrl: './payment-stats.component.html',
  styleUrls: ['./payment-stats.component.scss']
})
export class PaymentStatsComponent implements OnInit {

  stats!: PaymentStats;
  loading = true;

  constructor(private paymentService: PaymentService, private adminPaymentService: AdminPaymentService) {}

  ngOnInit(): void {
    this.adminPaymentService.getPaymentStats().subscribe({
      next: res => {
        this.stats = res;
        this.loading = false;
      },
      error: err => {
        console.error(err);
        alert('Failed to load payment stats');
        this.loading = false;
      }
    });
  }
}
