import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { HousekeepingRoutingModule } from './housekeeping-routing.module';
import { TasksListComponent } from './components/tasks-list/tasks-list.component';
import { TasksFormComponent } from './components/tasks-form/tasks-form.component';
import { TasksDetailComponent } from './components/tasks-detail/tasks-detail.component';
import { LaundryListComponent } from './components/laundry-list/laundry-list.component';
import { LaundryFormComponent } from './components/laundry-form/laundry-form.component';
import { MaintenanceListComponent } from './components/maintenance-list/maintenance-list.component';
import { MaintenanceFormComponent } from './components/maintenance-form/maintenance-form.component';
import { AssetLogListComponent } from './components/asset-log-list/asset-log-list.component';
import { AssetLogFormComponent } from './components/asset-log-form/asset-log-form.component';
import { HousekeepingStatsComponent } from './components/housekeeping-stats/housekeeping-stats.component';
import { SharedModule } from '../shared/shared.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { AdminHousekeepingComponent } from './components/admin-housekeeping/admin-housekeeping.component';


@NgModule({
  declarations: [
    TasksListComponent,
    TasksFormComponent,
    TasksDetailComponent,
    LaundryListComponent,
    LaundryFormComponent,
    MaintenanceListComponent,
    MaintenanceFormComponent,
    AssetLogListComponent,
    AssetLogFormComponent,
    HousekeepingStatsComponent,
    AdminHousekeepingComponent
  ],
  imports: [
    CommonModule,
    HousekeepingRoutingModule,
    SharedModule,
    FormsModule,
    ReactiveFormsModule
  ]
})
export class HousekeepingModule { }
