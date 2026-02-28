export interface Room {
  id: number;
  roomNumber: string;
  roomTypeId: number;
  roomType?: RoomType;
  status: 'AVAILABLE' | 'OCCUPIED' | 'MAINTENANCE' | 'BOOKED';
  floor: string;
  imageUrls?: string[];
  amenitiesNames: string[];
}

export interface RoomType {
  name: string;
  description: string;
  price: number;
}
