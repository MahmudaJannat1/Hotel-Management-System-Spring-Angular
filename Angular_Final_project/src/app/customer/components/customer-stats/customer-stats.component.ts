import { Component, OnInit } from '@angular/core';
import { CustomerStats } from '../../models/customer-stats.model';
import { CustomerService } from '../../services/customer.service';

@Component({
  selector: 'app-customer-stats',
  templateUrl: './customer-stats.component.html'
})
export class CustomerStatsComponent implements OnInit {

  stats!: CustomerStats;

  constructor(private customerService: CustomerService) { }

  ngOnInit(): void {
    this.customerService.getCustomerStats().subscribe(res => {
      this.stats = res;
    });
  }
}
