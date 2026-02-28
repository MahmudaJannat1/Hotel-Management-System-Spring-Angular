import { PaymentMethod } from "./payment-method.enum";

// export interface PaymentResponse {
//   id: number;
//   amount: number;
//   method: PaymentMethod;
//   paymentDate: string; // string because JSON date
//   bookingId: number;
// }


export interface PaymentResponse {
  id: number;
  bookingId: number;
  amount: string;  // payment amount as string
  method: string;
  paymentDate: string;
  status: string;

  // ✅ নতুন
  invoiceId: number;
  invoiceNumber?: string;
  totalAmount?: string;
  amountNumber?: number; // ✅ নতুন

}
