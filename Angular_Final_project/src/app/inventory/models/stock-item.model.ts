export interface StockItem {
  id: number;
  name: string;
  quantity: number;
  minQuantity?: number;
  categoryId: number;        // correspond to backend DTO
  categoryName: string;      // correspond to backend DTO
}




export interface AssetCategory {
  id: number;
  name: string;
  description?: string;
  active?: boolean;
}
