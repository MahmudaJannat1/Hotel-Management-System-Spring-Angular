import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { AdminEmployeeDashboardComponent } from './components/admin-employee-dashboard/admin-employee-dashboard.component';
import { EmployeeListComponent } from './components/employee-list/employee-list.component';
import { EmployeeFormComponent } from './components/employee-form/employee-form.component';
import { EmployeeDetailsComponent } from './components/employee-details/employee-details.component';
import { AttendanceListComponent } from './components/attendance-list/attendance-list.component';
import { TaskListComponent } from './components/task-list/task-list.component';
import { PayrollListComponent } from './components/payroll-list/payroll-list.component';
import { PayrollFormComponent } from './components/payroll-form/payroll-form.component';
import { AttendanceFormComponent } from './components/attendance-form/attendance-form.component';
import { TaskFormComponent } from './components/task-form/task-form.component';

const routes: Routes = [
  {
    path: 'admin',
    component: AdminEmployeeDashboardComponent,
    children: [
      { path: 'list', component: EmployeeListComponent },
      { path: 'create', component: EmployeeFormComponent },
      { path: 'edit/:id', component: EmployeeFormComponent },
      { path: 'details/:id', component: EmployeeDetailsComponent },
      
      // 📅 Attendance
      { path: 'attendance', component: AttendanceListComponent },
      { path: 'attendance/create', component: AttendanceFormComponent },
      { path: 'attendance/edit/:id', component: AttendanceFormComponent },

      // 🧠 Tasks
      { path: 'tasks', component: TaskListComponent },
      { path: 'tasks/create', component: TaskFormComponent },
      { path: 'tasks/edit/:id', component: TaskFormComponent },
      
      // 💰 Payroll
      { path: 'payroll', component: PayrollListComponent },
      { path: 'payroll/create', component: PayrollFormComponent },
      { path: 'payroll/edit/:id', component: PayrollFormComponent }
    ]
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class EmployeeRoutingModule { }
