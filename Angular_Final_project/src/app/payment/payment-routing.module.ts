import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { PaymentFormComponent } from './components/payment-form/payment-form.component';
import { PaymentConfirmationComponent } from './components/payment-confirmation/payment-confirmation.component';
import { InvoiceViewComponent } from './components/invoice-view/invoice-view.component';
import { PaymentHistoryComponent } from './components/payment-history/payment-history.component';
import { PaymentStatsComponent } from './components/payment-stats/payment-stats.component';
import { InvoiceListComponent } from './components/invoice-list/invoice-list.component';

import { AuthGuard } from '../core/guards/auth.guard';
import { AdminGuard } from '../core/guards/admin.guard';
import { AdminPaymentComponent } from './components/admin-payment/admin-payment.component';

const routes: Routes = [

  // 👤 USER / CUSTOMER
  { path: 'new', component: PaymentFormComponent, canActivate: [AuthGuard] },
  { path: 'confirmation/:id', component: PaymentConfirmationComponent, canActivate: [AuthGuard] },
  { path: 'invoice/:id', component: InvoiceViewComponent, canActivate: [AuthGuard] },
  { path: 'history', component: PaymentHistoryComponent, canActivate: [AuthGuard] },

  // 👑 ADMIN PAYMENT PANEL
  {
    path: 'admin',
    canActivate: [AuthGuard, AdminGuard],
    children: [
      { path: '', component: AdminPaymentComponent },     // dashboard / stats
      { path: 'stats', component: PaymentStatsComponent },     // dashboard / stats
      { path: 'invoices', component: InvoiceListComponent }
      // future:
      // { path: 'list', component: AdminPaymentListComponent }
    ]
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class PaymentRoutingModule {}
