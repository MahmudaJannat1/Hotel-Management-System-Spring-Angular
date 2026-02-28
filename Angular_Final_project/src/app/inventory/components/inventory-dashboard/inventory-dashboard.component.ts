import { Component, OnInit } from '@angular/core';
import { InventoryStats } from '../../models/inventory.model';
import { InventoryService } from '../../services/inventory.service';

@Component({
  selector: 'app-inventory-dashboard',
  templateUrl: './inventory-dashboard.component.html',
  styleUrls: ['./inventory-dashboard.component.scss']
})
export class InventoryDashboardComponent implements OnInit {

  stats: InventoryStats | null = null;
  loading = true;

  constructor(private inventoryService: InventoryService) { }

  ngOnInit(): void {
    this.loadStats();
  }

  loadStats() {
    this.loading = true;
    this.inventoryService.getInventoryStats().subscribe({
      next: (res) => {
        this.stats = res;
        this.loading = false;
      },
      error: (err) => {
        console.error('Error fetching stats', err);
        this.loading = false;
      }
    });
  }

}
