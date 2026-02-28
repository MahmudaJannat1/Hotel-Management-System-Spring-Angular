import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { PaymentRoutingModule } from './payment-routing.module';
import { PaymentSuccessComponent } from './components/payment-success/payment-success.component';
import { PaymentHistoryComponent } from './components/payment-history/payment-history.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { PaymentConfirmationComponent } from './components/payment-confirmation/payment-confirmation.component';
import { InvoiceViewComponent } from './components/invoice-view/invoice-view.component';
import { PaymentStatsComponent } from './components/payment-stats/payment-stats.component';
import { InvoiceListComponent } from './components/invoice-list/invoice-list.component';
import { PaymentFormComponent } from './components/payment-form/payment-form.component';
import { AdminPaymentComponent } from './components/admin-payment/admin-payment.component';
import { SharedModule } from '../shared/shared.module';


@NgModule({
  declarations: [
    PaymentFormComponent,
    PaymentSuccessComponent,
    PaymentHistoryComponent,
    PaymentConfirmationComponent,
    InvoiceViewComponent,
    PaymentStatsComponent,
    InvoiceListComponent,
    AdminPaymentComponent
  ],
  imports: [
    CommonModule,
    PaymentRoutingModule,
    FormsModule,
    ReactiveFormsModule,
    SharedModule
  ]
})
export class PaymentModule { }
