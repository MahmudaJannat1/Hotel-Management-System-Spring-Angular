import { Role } from "src/app/auth/models/role.model";

export interface AdminStatisticsDTO {

  // Users
  totalUsers: number;
  enabledUsers: number;
  roles: Role[];

  // Bookings
  totalBookings: number;
  pendingBookings: number;
  cancelledBookings: number;
  completedBookings: number;

  // Revenue
  totalRevenue: number;

  // Customers
  totalCustomers: number;

  // Rooms
  totalRooms: number;
  activeRooms: number;
  occupiedRooms: number;

  // Hotels
  totalHotels: number;
  activeHotels: number;
  totalRoomsInHotels: number;
  occupiedRoomsInHotels: number;
}
