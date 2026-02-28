import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { CustomerListComponent } from './components/customer-list/customer-list.component';
import { CustomerFormComponent } from './components/customer-form/customer-form.component';
import { CustomerStatsComponent } from './components/customer-stats/customer-stats.component';
import { SharedModule } from '../shared/shared.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { CustomerRoutingModule } from './customer-routing.module';
import { RouterModule } from '@angular/router';

@NgModule({
  declarations: [
    CustomerListComponent,
    CustomerFormComponent,
    CustomerStatsComponent,

  ],
  imports: [
   CommonModule,
  SharedModule,
  FormsModule,
  ReactiveFormsModule,
  HttpClientModule,
  RouterModule,          // ✅ needed for routerLink
  CustomerRoutingModule
  ]
})
export class CustomerModule { }
