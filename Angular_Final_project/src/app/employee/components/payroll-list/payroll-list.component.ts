import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Payroll } from '../../models/payroll.model';
import { PayrollService } from '../../services/payroll.service';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-payroll-list',
  templateUrl: './payroll-list.component.html',
  styleUrls: ['./payroll-list.component.scss']
})
export class PayrollListComponent implements OnInit {

  payrolls: Payroll[] = [];

  constructor(
    private prservice: PayrollService,
    private router: Router,
    private route: ActivatedRoute,
    private http: HttpClient
  ) {}

employeesMap = new Map<number, string>();

ngOnInit(): void {
  this.load(); // just call once
}

load() {
  this.prservice.getAll().subscribe(res => {
    console.log('Payroll API response:', res);
    this.payrolls = res;
  });
}



  edit(id: number) {
    this.router.navigate(['/employee/admin/payroll/edit', id]);
  }

  delete(id: number) {
    if (confirm('Delete payroll?')) {
      this.prservice.delete(id).subscribe(() => this.load());
    }
  }

downloadPayslip(id: number) {
  this.prservice.downloadPayslip(id).subscribe(blob => {
    const url = window.URL.createObjectURL(blob);
    const a = document.createElement('a');
    a.href = url;
    a.download = 'payslip.pdf';
    a.click();
  });
}


}
