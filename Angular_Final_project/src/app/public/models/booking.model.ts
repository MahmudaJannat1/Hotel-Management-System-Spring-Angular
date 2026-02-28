export interface BookingItem {
  itemName: string;
  quantity: number;
  price: number;
}

export interface BookingRequest {
  roomId: number;
  customerId: number;
  checkInDate: string;
  checkOutDate: string;
  items: BookingItem[];
}

export interface BookingResponse {
  bookingCode: string;
  customerName: string;
  roomNumber: string;
  checkInDate: string;
  checkOutDate: string;
  status: string;
  items: BookingItem[];
}
