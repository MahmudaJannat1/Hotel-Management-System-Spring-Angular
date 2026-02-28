import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AdminDashboardComponent } from './components/dashboard/admin-dashboard.component';
import { StatisticsComponent } from './components/statistics/statistics.component';
import { AdminGuard } from '../core/guards/admin.guard';

const routes: Routes = [
  {
    path: 'dashboard',
    component: AdminDashboardComponent,
    canActivate: [AdminGuard] // optional, যদি role guard থাকে
  },
  {
    path: 'statistics/users',
    component: StatisticsComponent, // user stats page
    canActivate: [AdminGuard]
  },
  {
    path: 'statistics/system',
    component: StatisticsComponent, // system stats page, shared component
    canActivate: [AdminGuard]
  },
  {
    path: '',
    redirectTo: 'dashboard',
    pathMatch: 'full'
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AdminRoutingModule { }
