import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router'; // ✅ Import Router
import { AdminDashboardService } from '../../services/admin.service';
import { AdminDashboard } from '../../models/admin-dashboard.model';

@Component({
  selector: 'app-admin-dashboard',
  templateUrl: './admin-dashboard.component.html',
  styleUrls: ['./admin-dashboard.component.scss']
})
export class AdminDashboardComponent implements OnInit {
refreshData() {
throw new Error('Method not implemented.');
}

  data!: AdminDashboard;
  loading = true;
currentTime: string | number | Date | undefined;

  constructor(
    private dashboardService: AdminDashboardService,
    private router: Router // ✅ Inject Router
    
  ) {}

  ngOnInit() {
    this.dashboardService.getStats().subscribe(res => {
      this.data = res;
      this.loading = false;
    });
  }

goToModule(module: string) {
  const routesMap: Record<string, string> = {
    booking: '/booking/admin',
    rooms: '/rooms/admin/list',
    payment: '/payment/admin',
    inventory: '/inventory',
    customers: '/customers',
    employee: '/employee/admin',
    housekeeping: '/housekeeping/admin',
    hotels: '/hotels/admin',
    invoices: '/payment/admin/invoices'
  };

  const route = routesMap[module];

  if (route) {
    this.router.navigate([route]);
  } else {
    console.warn('No route found for module:', module);
  }
}
}
