import { Role } from "src/app/auth/models/role.model";

export interface AdminUserStatisticsDTO {
  totalUsers: number;
  enabledUsers: number;
  roles: Role[];
}
