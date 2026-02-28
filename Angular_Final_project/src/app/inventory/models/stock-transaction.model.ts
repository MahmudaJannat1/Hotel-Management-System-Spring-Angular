export interface StockTransaction {
  id: number;
  stockItemId: number;
  quantity: number;
  type: 'IN' | 'OUT';
}
