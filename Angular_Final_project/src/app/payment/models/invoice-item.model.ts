export interface InvoiceItem {
  description: string;
  quantity: number;
  amount: string; // BigDecimal comes as string from backend
}
