import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { BookingListComponent } from './components/booking-list/booking-list.component';
import { BookingDetailComponent } from './components/booking-detail/booking-detail.component';
import { BookingFormComponent } from './components/booking-form/booking-form.component';
import { BookingStatsComponent } from './components/booking-stats/booking-stats.component';
import { AuthGuard } from '../core/guards/auth.guard';
import { AdminGuard } from '../core/guards/admin.guard';
import { BookingConfirmationComponent } from './components/booking-confirmation/booking-confirmation.component';

const routes: Routes = [
  // Public Booking Pages
  { path: '', component: BookingListComponent },
  { path: 'details/:code', component: BookingDetailComponent },
  { path: 'create', component: BookingFormComponent, canActivate: [AuthGuard] },
  { path: 'confirmation/:code', component: BookingConfirmationComponent},

  // Admin Booking Pages
  { path: 'admin', component: BookingListComponent, canActivate: [AuthGuard, AdminGuard] },
  { path: 'admin/stats', component: BookingStatsComponent, canActivate: [AuthGuard, AdminGuard] },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class BookingRoutingModule {}
