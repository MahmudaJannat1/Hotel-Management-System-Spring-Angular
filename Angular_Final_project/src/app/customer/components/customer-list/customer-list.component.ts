// import { Component, OnInit } from '@angular/core';
// import { CustomerService } from '../../services/customer.service';
// import { Customer } from '../../models/customer.model';
// import { Router } from '@angular/router';

// @Component({
//   selector: 'app-customer-list',
//   templateUrl: './customer-list.component.html'
// })
// export class CustomerListComponent implements OnInit {

//   customers: Customer[] = [];

//   constructor(
//     private customerService: CustomerService,
//     private router: Router
//   ) { }

//   ngOnInit(): void {
//     this.loadCustomers();
//   }

//   loadCustomers() {
//     this.customerService.getAllCustomers().subscribe(res => {
//       this.customers = res;
//     });
//   }

//   deleteCustomer(id: number) {
//     if(confirm('Are you sure you want to delete this customer?')) {
//       this.customerService.deleteCustomer(id).subscribe(() => {
//         this.loadCustomers();
//       });
//     }
//   }
// }

import { Component, OnInit } from '@angular/core';
import { CustomerService } from '../../services/customer.service';
import { Customer } from '../../models/customer.model';
import { CustomerStats } from '../../models/customer-stats.model';
import { Router } from '@angular/router';

@Component({
  selector: 'app-customer-list',
  templateUrl: './customer-list.component.html',
  styleUrls: ['./customer-list.component.scss']
})
export class CustomerListComponent implements OnInit {

  customers: Customer[] = [];
  stats!: CustomerStats; // ✅ Stats data

  constructor(
    private customerService: CustomerService,
    private router: Router
  ) { }

  ngOnInit(): void {
    this.loadStats();      // load stats first
    this.loadCustomers();  // then load customer list
  }

  // 🔹 Load stats from backend
  loadStats() {
    this.customerService.getCustomerStats().subscribe(res => {
      this.stats = res;
    });
  }

  // 🔹 Load all active customers
  loadCustomers() {
    this.customerService.getAllCustomers().subscribe(res => {
      this.customers = res;
    });
  }

  // 🔹 Soft delete customer
  deleteCustomer(id: number) {
    if(confirm('Are you sure you want to delete this customer?')) {
      this.customerService.deleteCustomer(id).subscribe(() => {
        this.loadCustomers();
        this.loadStats(); // ✅ update stats after delete
      });
    }
  }
}
