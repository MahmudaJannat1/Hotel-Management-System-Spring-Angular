import { MaintenanceStatus } from '../enums/maintenance-status.enum';

export interface MaintenanceRequest {
  id: number;
  description: string;
  status: MaintenanceStatus;
}