import { InvoiceItem } from "./invoice-item.model";

export interface InvoiceResponse {
  id: number;
  invoiceNumber: string;
  invoiceDate: string;
  bookingId: number;
  totalAmount: string; // Backend থেকে string (BigDecimal)
  items: InvoiceItem[];
  
  // 💡 optional: frontend convenience
  amountNumber?: number; // নতুন field, string → number conversion এর জন্য
}
