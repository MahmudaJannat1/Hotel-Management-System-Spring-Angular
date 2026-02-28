import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { EmployeeService } from '../../services/employee.service';
import { Department, Employee, EmployeeRole } from '../../models/employee.model';


@Component({
selector: 'app-employee-list',
templateUrl: './employee-list.component.html',
styleUrls:['./employee-list.component.scss']

})
export class EmployeeListComponent implements OnInit {


employees: Employee[] = [];
roles = Object.values(EmployeeRole);
departments = Object.values(Department);


constructor(
private empservice: EmployeeService,
private router: Router
) {}


ngOnInit(): void {
this.load();
}


load(): void {
this.empservice.getAll().subscribe(res => {
this.employees = res;
});
}


edit(id: number): void {
// ✅ Fixed route for Admin lazy-loaded Employee module
this.router.navigate(['/employee/admin/edit', id]);
}


delete(id: number): void {
if (confirm('Delete employee?')) {
this.empservice.delete(id).subscribe(() => this.load());
}
}
}