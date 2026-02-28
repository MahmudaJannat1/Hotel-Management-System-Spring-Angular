import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
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
import { AdminHousekeepingComponent } from './components/admin-housekeeping/admin-housekeeping.component';

const routes: Routes = [
  // Tasks
  { path: 'tasks', component: TasksListComponent },
  { path: 'tasks/new', component: TasksFormComponent },
  { path: 'tasks/:id', component: TasksDetailComponent },
  { path: 'tasks/edit/:id', component: TasksFormComponent },

  // Laundry  
  { path: 'laundry', component: LaundryListComponent },
  { path: 'laundry/new', component: LaundryFormComponent },
  { path: 'laundry/edit/:id', component: LaundryFormComponent },

  // Maintenance
  { path: 'maintenance', component: MaintenanceListComponent },
  { path: 'maintenance/new', component: MaintenanceFormComponent },
  { path: 'maintenance/edit/:id', component: MaintenanceFormComponent },

  // Asset Maintenance Logs
  { path: 'asset-log', component: AssetLogListComponent },
  { path: 'asset-log/new', component: AssetLogFormComponent },
  { path: 'asset-log/edit/:id', component: AssetLogFormComponent },

  { path: 'admin', component: AdminHousekeepingComponent },

  // Default redirect
  { path: '', redirectTo: 'admin', pathMatch: 'full' }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class HousekeepingRoutingModule { }
