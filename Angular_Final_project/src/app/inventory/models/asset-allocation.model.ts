export interface AssetAllocation {
  id: number;
  assetId: number;
  allocatedTo: string;
  allocatedAt: string; // ISO string from backend
}
