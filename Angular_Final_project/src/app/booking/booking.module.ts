import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { BookingRoutingModule } from './booking-routing.module';
import { BookingListComponent } from './components/booking-list/booking-list.component';
import { BookingFormComponent } from './components/booking-form/booking-form.component';
import { SharedModule } from '../shared/shared.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { BookingDetailComponent } from './components/booking-detail/booking-detail.component';
import { BookingStatsComponent } from './components/booking-stats/booking-stats.component';
import { RouterModule } from '@angular/router';
import { NgChartsModule } from 'ng2-charts';
import { BookingConfirmationComponent } from './components/booking-confirmation/booking-confirmation.component';


@NgModule({
  declarations: [
    BookingListComponent,
    BookingFormComponent,
    BookingDetailComponent,
    BookingStatsComponent,
    BookingConfirmationComponent
  ],
  imports: [
    CommonModule,
    BookingRoutingModule,
    SharedModule,
    FormsModule,
    ReactiveFormsModule,
    RouterModule,
    NgChartsModule  

  ]
})
export class BookingModule { }
