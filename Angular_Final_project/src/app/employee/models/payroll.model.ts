export enum PayrollStatus {
  PENDING = 'PENDING',
  PAID = 'PAID',
  CANCELLED = 'CANCELLED'
}

export enum PaymentMethod { // matches backend SalaryTypeEnum
  CASH = 'CASH',
  BANK = 'BANK',
  MOBILE_BANKING = 'MOBILE_BANKING'
}

// Frontend Payroll interface (aligned with backend PayrollDTO)
export interface Payroll {
  id: number;
  employeeId: number;
  employeeName?: string;       // UI only
  salaryType: PaymentMethod;   // backend field
  amount: number;              // backend field (calculated)
  paymentDate?: string;        // backend field
  status: PayrollStatus;       // backend field

  // Optional UI-only fields
  month?: string;
  baseSalary?: number;
  bonus?: number;
  deduction?: number;
  netSalary?: number;
}
