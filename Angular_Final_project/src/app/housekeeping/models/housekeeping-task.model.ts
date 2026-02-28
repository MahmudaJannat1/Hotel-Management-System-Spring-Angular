import { TaskStatus } from "../enums/task-status.enum";

export interface HousekeepingTask {
  id: number;
  roomId: number;
  description: string;
  status: TaskStatus;
  assignedTo?: string;
  createdAt: Date;
  updatedAt: Date;
  
}