import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { InvoiceService } from '../../services/invoice.service';
import { InvoiceResponse } from '../../models/invoice-response.model';

@Component({
  selector: 'app-invoice-view',
  templateUrl: './invoice-view.component.html',
  styleUrls: ['./invoice-view.component.scss']
})
export class InvoiceViewComponent implements OnInit {

  invoice!: InvoiceResponse;
  invoiceId!: number;

  constructor(
    private route: ActivatedRoute,
    private invoiceService: InvoiceService
  ) { }

ngOnInit(): void {
  // Get invoiceId from route param
  const param = this.route.snapshot.paramMap.get('id');
if (!param) {
  alert('Invoice not specified!');
  return;
}
this.invoiceId = +param;


  // Fetch invoice
  this.invoiceService.get(this.invoiceId).subscribe({
    next: (res: InvoiceResponse) => this.invoice = res,
    error: err => {
      console.error(err);
      alert('Invoice could not be loaded!');
    }
  });
}


  // Print only the invoice
  printInvoice() {
    window.print();
  }
}
