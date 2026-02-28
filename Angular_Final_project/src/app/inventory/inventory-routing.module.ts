import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AssetCategoryListComponent } from './components/asset-category-list/asset-category-list.component';
import { AssetCategoryFormComponent } from './components/asset-category-form/asset-category-form.component';
import { InventoryDashboardComponent } from './components/inventory-dashboard/inventory-dashboard.component';
import { StockItemListComponent } from './components/stock-item-list/stock-item-list.component';
import { StockItemFormComponent } from './components/stock-item-form/stock-item-form.component';
import { AssetAllocationComponent } from './components/asset-allocation/asset-allocation.component';

const routes: Routes = [
  { path: '', component: InventoryDashboardComponent },
  { path: 'asset/list', component: AssetCategoryListComponent },
  { path: 'asset/new', component: AssetCategoryFormComponent },
  { path: 'asset/edit/:id', component: AssetCategoryFormComponent },
  { path: 'stock/list', component: StockItemListComponent },
  { path: 'stock/new', component: StockItemFormComponent },
  { path: 'stock/edit/:id', component: StockItemFormComponent },
  { path: 'alocated/list', component: AssetAllocationComponent},
  { path: 'alocated/new', component: AssetAllocationComponent },
  { path: 'alocated/edit/:id', component: AssetAllocationComponent },
];


@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class InventoryRoutingModule { }
