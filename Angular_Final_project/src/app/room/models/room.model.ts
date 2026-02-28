import { RoomType } from "./room-type.model";

export enum RoomStatus {
  AVAILABLE = 'AVAILABLE',
  OCCUPIED = 'OCCUPIED',
  MAINTENANCE = 'MAINTENANCE',
  BOOKED = 'BOOKED'
}

// Model
export interface Room {
  id: number;
  roomNumber: string;
  floor?: string;
  status: RoomStatus;       // backend string automatically match করবে
  roomType: RoomType;
  imageUrls?: string[];
  amenitiesNames?: string[];
  pricePerNight: number;   // backend field mapping
  sizeSqm?: number;
  maxGuests?: number;
  adults?: number;
  shortDescription?: string;
}



