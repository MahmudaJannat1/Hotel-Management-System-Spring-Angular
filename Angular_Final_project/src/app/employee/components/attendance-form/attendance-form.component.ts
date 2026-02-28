import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { AttendanceService } from '../../services/attendance.service';
import { EmployeeService } from '../../services/employee.service';
import { Router } from '@angular/router';
import { Attendance, ShiftType } from '../../models/attendance.model';


@Component({
  selector: 'app-attendance-form',
  templateUrl: './attendance-form.component.html',
    styleUrls:['./attendance-form.component.scss']

})
export class AttendanceFormComponent implements OnInit {
  form!: FormGroup;
  employees: any[] = [];
shifts = Object.values(ShiftType); // ["MORNING","EVENING","NIGHT"]


  constructor(
    private fb: FormBuilder,
    private service: AttendanceService,
    private employeeService: EmployeeService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.form = this.fb.group({
      employeeId: [null, Validators.required],
      date: [new Date().toISOString().substring(0,10), Validators.required],
      checkIn: [''],
      checkOut: [''],
      shift: ['MORNING', Validators.required]
    });

    this.employeeService.getAll().subscribe(res => this.employees = res);
  }

  submit() {
    if (this.form.invalid) return;

    const dto: Attendance = this.form.value;
    this.service.mark(dto).subscribe(() => this.router.navigate(['/employee/admin/attendance']));
  }
}
