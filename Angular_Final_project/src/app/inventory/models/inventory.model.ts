export interface InventoryStats {
  totalAssets: number;
  allocatedAssets: number;
  availableAssets: number;

  totalStockItems: number;
  lowStockItems: number;
  totalTransactions: number;
}
