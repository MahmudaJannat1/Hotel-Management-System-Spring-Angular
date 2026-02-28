import { Component, OnInit } from '@angular/core';
import { StockItem } from '../../models/stock-item.model';
import { StockItemService } from '../../services/stock-item.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-stock-item-list',
  templateUrl: './stock-item-list.component.html',
  styleUrls: ['./stock-item-list.component.scss']
})
export class StockItemListComponent implements OnInit {

  items: StockItem[] = [];
  loading = true;

  constructor(private stockService: StockItemService, private router: Router) {}

  ngOnInit(): void {
    this.loadItems();
  }

  loadItems() {
    this.loading = true;
    this.stockService.getAll().subscribe({
      next: res => { this.items = res; this.loading = false; },
      error: () => this.loading = false
    });
  }
  // ✅ Delete properly
  deleteItem(id: number) {
    if(confirm('Are you sure to delete this item?')) {
      this.stockService.delete(id).subscribe({
        next: () => this.loadItems(),
        error: err => console.error('Delete failed', err)
      });
    }
  }

  // ✅ Navigate to edit page
  editItem(id: number) {
    this.router.navigate(['/inventory/stock/edit', id]);
  }


  
}


