// room-dto.model.ts
export interface RoomDTO {
   roomNumber: string;
  roomTypeId: number;
  status: string;           // RoomStatus as string
  floor?: string;
  imageUrls?: string[];
  amenitiesNames?: string[];
  pricePerNight?: number;
  sizeSqm?: number;
  maxGuests?: number;
  adults?: number;
  shortDescription?: string;
}
