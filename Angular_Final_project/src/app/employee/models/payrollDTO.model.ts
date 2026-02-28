export interface PayrollDTO {
  id?: number;             // optional for create
  employeeId: number;
  salaryType: PaymentMethod; // maps to backend
  amount: number;           // net salary
  paymentDate?: string;
  status: PayrollStatus;
}

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
