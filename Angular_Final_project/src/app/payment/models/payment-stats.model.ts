export interface PaymentStats {
  totalPayments: number;
  completedPayments: number;
  pendingPayments: number;
  cancelledPayments: number;
  totalRevenue: number;
  totalInvoices: number;
  paidInvoices: number;
  pendingInvoices: number;
}
