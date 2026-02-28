

export interface Employee {
  id: number;
  name: string;
  email: string;
  phone: string;
  role: EmployeeRole;
  department: Department;
  joiningDate: string; // ISO date
  salary: number;
}


export enum EmployeeRole {
  ADMIN = 'ADMIN',
  MANAGER = 'MANAGER',
  STAFF = 'STAFF'
}

export enum Department {
  HOUSEKEEPING = 'HOUSEKEEPING',
  FRONT_DESK = 'FRONT_DESK',
  KITCHEN = 'KITCHEN',
  MAINTENANCE = 'MAINTENANCE'
}

