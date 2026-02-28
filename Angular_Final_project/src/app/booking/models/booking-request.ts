export interface BookingRequest {
  roomId: number;
  customerId: number;
  checkInDate: string;
  checkOutDate: string;
  items: BookingItem[];
  adults: number;          // new
  children: number;        // new
  roomType: RoomType;      // new
}

export interface BookingItem {
  itemName: string;
  quantity: number;
  price: number;
}

export enum RoomType {
  SINGLE = 'SINGLE',
  DOUBLE = 'DOUBLE',
  DELUXE = 'DELUXE',
  SUITE = 'SUITE'
}


