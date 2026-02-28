import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { InventoryRoutingModule } from './inventory-routing.module';
import { InventoryDashboardComponent } from './components/inventory-dashboard/inventory-dashboard.component';
import { AssetCategoryListComponent } from './components/asset-category-list/asset-category-list.component';
import { AssetCategoryFormComponent } from './components/asset-category-form/asset-category-form.component';
import { AssetAllocationComponent } from './components/asset-allocation/asset-allocation.component';
import { StockItemListComponent } from './components/stock-item-list/stock-item-list.component';
import { StockItemFormComponent } from './components/stock-item-form/stock-item-form.component';
import { StockTransactionListComponent } from './components/stock-transaction-list/stock-transaction-list.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { SharedModule } from '../shared/shared.module';
import { RouterModule } from '@angular/router';

@NgModule({
  declarations: [
    InventoryDashboardComponent,
    AssetCategoryListComponent,
    AssetCategoryFormComponent,
    AssetAllocationComponent,
    StockItemListComponent,
    StockItemFormComponent,
    StockTransactionListComponent,
  ],
  imports: [
    CommonModule,
    ReactiveFormsModule,
    FormsModule,
    HttpClientModule,
    InventoryRoutingModule,
    SharedModule,
  ],
})
export class InventoryModule {}
