export interface AdminEmployeeDashboard {

  // EMPLOYEE
  totalEmployees: number;
  activeEmployees: number;
  inactiveEmployees: number;

  // ATTENDANCE
  todayPresent: number;
  todayAbsent: number;

  // TASKS
  totalTasks: number;
  pendingTasks: number;
  inProgressTasks: number;
  completedTasks: number;

  // PAYROLL
  totalPayrolls: number;
  paidPayrolls: number;
  pendingPayrolls: number;
  totalSalaryPaid: number;
}
