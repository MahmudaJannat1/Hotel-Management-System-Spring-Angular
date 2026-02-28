import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { AdminRoutingModule } from './admin-routing.module';
import { StatisticsComponent } from './components/statistics/statistics.component';
import { AdminDashboardComponent } from './components/dashboard/admin-dashboard.component';
import { SharedModule } from '../shared/shared.module';

@NgModule({
  declarations: [
    AdminDashboardComponent,
    StatisticsComponent
  ],
  imports: [
    CommonModule,
    AdminRoutingModule,
    SharedModule,
    
  ]
})
export class AdminModule { }
