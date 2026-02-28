
import { BookingItemResponse } from "./booking-item-response";


export interface BookingResponse {
  id: number;            // ✅ add this
  bookingCode: string;
  customerId: number;
  customerName: string;
  roomId: number;
  roomNumber: string;
  checkInDate: string;
  checkOutDate: string;
  status: string;
  items: BookingItemResponse[];
  adults: number;
  children: number;
  numberOfNights: number;
 roomType: keyof typeof RoomType; // ✅ this allows "SINGLE", "DOUBLE", etc. as string  
  
}


export enum RoomType {
  Deluxe = 'Deluxe Room',
  Executive = 'Executive Suite',
  SeaViewRoom = 'Sea View Room',
  FamilyRoom = 'Family Room',
  CorporateRoom = 'Corporate Room'
}



