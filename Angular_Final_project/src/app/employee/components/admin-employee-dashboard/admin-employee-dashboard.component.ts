import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AdminEmployeeDashboardService } from '../../services/admin-employee-dashboard.service';
import { AdminEmployeeDashboard } from '../../models/admin-employee-dashboard.model';

@Component({
  selector: 'app-admin-employee-dashboard',
  templateUrl: './admin-employee-dashboard.component.html',
  styleUrls: ['./admin-employee-dashboard.component.scss']
})
export class AdminEmployeeDashboardComponent implements OnInit {

  loading = true;
  data!: AdminEmployeeDashboard;

  constructor(
    private service: AdminEmployeeDashboardService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.service.getDashboardStats().subscribe({
      next: res => {
        this.data = res;
        this.loading = false;
      },
      error: () => this.loading = false
    });
  }

  goTo(path: string) {
    this.router.navigate([path]);
  }
}
