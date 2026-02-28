import { LaundryStatus } from '../enums/laundry-status.enum';

export interface LaundryRequest {
  id: number;
  description: string;
  status: LaundryStatus;
}
