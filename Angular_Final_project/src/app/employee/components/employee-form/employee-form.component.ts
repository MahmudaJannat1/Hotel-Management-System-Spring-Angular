import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { EmployeeService } from '../../services/employee.service';
import { Department, EmployeeRole } from '../../models/employee.model';


@Component({
  selector: 'app-employee-form',
  templateUrl: './employee-form.component.html',
  styleUrls:['./employee-form.component.scss']
})
export class EmployeeFormComponent implements OnInit {

  roles = Object.values(EmployeeRole);
  departments = Object.values(Department);

  employeeId?: number;

  form = this.fb.group({
    name: ['', Validators.required],
    email: ['', [Validators.required, Validators.email]],
    phone: ['', Validators.required],
    role: ['', Validators.required],
    department: ['', Validators.required],
    joiningDate: ['', Validators.required],
    salary: [0, Validators.required]
  });

  constructor(
    private fb: FormBuilder,
    private service: EmployeeService,
    private route: ActivatedRoute,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.employeeId = Number(this.route.snapshot.paramMap.get('id'));

    if (this.employeeId) {
      this.service.getById(this.employeeId).subscribe(emp => {
        this.form.patchValue(emp);
      });
    }
  }

  submit(): void {
    if (this.form.invalid) return;

    if (this.employeeId) {
      this.service.update(this.employeeId, this.form.value as any)
        .subscribe(() => this.router.navigate(['/employee/admin/list']));
    } else {
      this.service.create(this.form.value as any)
        .subscribe(() => this.router.navigate(['/employee/admin/list']));
    }
  }
}
