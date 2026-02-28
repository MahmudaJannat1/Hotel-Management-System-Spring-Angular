import { EmployeeService } from './../../services/employee.service';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { PayrollService } from '../../services/payroll.service';
import { Employee } from '../../models/employee.model';
import { PayrollStatus, PaymentMethod } from '../../models/payroll.model';
import { PayrollDTO } from '../../models/payrollDTO.model';

@Component({
  selector: 'app-payroll-form',
  templateUrl: './payroll-form.component.html',
  styleUrls: ['./payroll-form.component.scss']
})
export class PayrollFormComponent implements OnInit {

  payrollId?: number;

  statuses = Object.values(PayrollStatus);
  methods = Object.values(PaymentMethod);
form = this.fb.group({
  employeeId: [null as number | null, Validators.required],
  baseSalary: [0, Validators.required],
  bonus: [0],
  deduction: [0],
  netSalary: [{ value: 0, disabled: true }], // ✅ DISABLED
  status: [PayrollStatus.PENDING, Validators.required],
  salaryType: [PaymentMethod.CASH, Validators.required],
  paymentDate: ['']
});


  employees: Employee[] = [];

  constructor(
    private fb: FormBuilder,
    private service: PayrollService,
    private route: ActivatedRoute,
    private router: Router,
    private employeeService: EmployeeService
  ) {}

  ngOnInit(): void {
    // Load employee list for select
    this.employeeService.getAll().subscribe(res => this.employees = res);

    // Check if edit mode
    this.payrollId = Number(this.route.snapshot.paramMap.get('id'));
    if (this.payrollId) {
      this.service.getById(this.payrollId)
        .subscribe(res => {
          // Patch backend fields only
          this.form.patchValue({
            employeeId: res.employeeId,
            netSalary: res.amount,
            status: res.status,
            salaryType: res.salaryType,
            paymentDate: res.paymentDate
          });
        });
    }

    // Recalculate netSalary dynamically
    this.setupNetSalaryCalculation();
  }

setupNetSalaryCalculation() {
  this.form.valueChanges.subscribe(v => {
    const base = Number(v.baseSalary ?? 0);
    const bonus = Number(v.bonus ?? 0);
    const deduction = Number(v.deduction ?? 0);

    const net = base + bonus - deduction;

    this.form.patchValue(
      { netSalary: net },
      { emitEvent: false }
    );
  });
}


submit() {
  if (this.form.invalid) return;

  const raw = this.form.getRawValue();

  const payload: PayrollDTO = {
    employeeId: raw.employeeId!,
    amount: raw.netSalary!,
    salaryType: raw.salaryType!,
    paymentDate: raw.paymentDate || undefined,
    status: raw.status!
  };

  const req$ = this.payrollId
    ? this.service.update(this.payrollId, payload)
    : this.service.create(payload);

  req$.subscribe(() => {
    this.router.navigate(['/employee/admin/payroll']);
  });
}

}
