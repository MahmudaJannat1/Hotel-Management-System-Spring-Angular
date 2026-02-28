export enum TaskTypeEnum {
  HOUSEKEEPING = 'HOUSEKEEPING',
  MAINTENANCE = 'MAINTENANCE',
  OTHER = 'OTHER'
}

export enum TaskStatusEnum {
  PENDING = 'PENDING',
  IN_PROGRESS = 'IN_PROGRESS',
  DONE = 'DONE'
}

export interface EmployeeTask {
  id: number;
  employeeId: number;
  taskType: TaskTypeEnum;
  taskId: number; // optional if linked to housekeeping/maintenance
  description: string;
  status: TaskStatusEnum;
  assignedDate?: string;
  completedDate?: string;
}
