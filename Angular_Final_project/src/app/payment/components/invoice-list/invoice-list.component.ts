import { Component, OnInit } from '@angular/core';
import { InvoiceService } from '../../services/invoice.service';
import { InvoiceStats } from '../../models/invoice-stats.model';

@Component({
  selector: 'app-invoice-list',
  templateUrl: './invoice-list.component.html',
  styleUrls: ['./invoice-list.component.scss']
})
export class InvoiceListComponent implements OnInit {

  stats!: InvoiceStats;
  loading = true;

  constructor(private invoiceService: InvoiceService) {}

  ngOnInit(): void {
    this.invoiceService.getInvoiceStats().subscribe({
      next: res => {
        this.stats = res;
        this.loading = false;
      },
      error: err => {
        console.error(err);
        alert('Failed to load invoice stats');
        this.loading = false;
      }
    });
  }
}
