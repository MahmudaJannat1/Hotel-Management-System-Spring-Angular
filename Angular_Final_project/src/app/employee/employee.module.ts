import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { EmployeeRoutingModule } from './employee-routing.module';
import { EmployeeListComponent } from './components/employee-list/employee-list.component';
import { EmployeeFormComponent } from './components/employee-form/employee-form.component';
import { EmployeeDetailsComponent } from './components/employee-details/employee-details.component';
import { AttendanceListComponent } from './components/attendance-list/attendance-list.component';
import { AttendanceFormComponent } from './components/attendance-form/attendance-form.component';
import { TaskListComponent } from './components/task-list/task-list.component';
import { TaskFormComponent } from './components/task-form/task-form.component';
import { PayrollListComponent } from './components/payroll-list/payroll-list.component';
import { PayrollFormComponent } from './components/payroll-form/payroll-form.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { SharedModule } from '../shared/shared.module';
import { RouterModule } from '@angular/router';
import { AdminEmployeeDashboardComponent } from './components/admin-employee-dashboard/admin-employee-dashboard.component';


@NgModule({
  declarations: [
    EmployeeListComponent,
    EmployeeFormComponent,
    EmployeeDetailsComponent,
    AttendanceListComponent,
    AttendanceFormComponent,
    TaskListComponent,
    TaskFormComponent,
    PayrollListComponent,
    PayrollFormComponent,
    
    AdminEmployeeDashboardComponent
  ],
  imports: [
    CommonModule,
    EmployeeRoutingModule,
    FormsModule,
    ReactiveFormsModule,
    SharedModule,
    RouterModule
  ]
})
export class EmployeeModule { }
