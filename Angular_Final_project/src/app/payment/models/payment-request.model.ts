import { PaymentMethod } from "./payment-method.enum";

export interface PaymentRequest {
  bookingId: number;
  amount: number;
  method: PaymentMethod;
}
