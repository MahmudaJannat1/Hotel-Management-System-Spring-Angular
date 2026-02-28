import { Component, OnInit } from '@angular/core';
import { AssetMaintenanceLog } from '../../models/asset-maintenance-log.model';
import { HousekeepingService } from '../../services/housekeeping.service';

@Component({
  selector: 'app-asset-log-list',
  templateUrl: './asset-log-list.component.html',
  styleUrls: ['./asset-log-list.component.scss']
})
export class AssetLogListComponent implements OnInit {

  logs: AssetMaintenanceLog[] = [];
  statuses = ['PENDING', 'IN_PROGRESS', 'DONE']; // optional if needed
  editingLogId: number | null = null;
  loading = true;

  constructor(private hkService: HousekeepingService) {}

  ngOnInit(): void {
    this.loadLogs();
  }

  loadLogs() {
    this.hkService.getAllAssetLogs().subscribe({
      next: res => {
        this.logs = res;
        this.loading = false;
      },
      error: () => alert('Failed to load logs')
    });
  }

  startEdit(id: number) {
    this.editingLogId = id;
  }

  cancelEdit() {
    this.editingLogId = null;
    this.loadLogs();
  }

  updateLog(log: AssetMaintenanceLog) {
    this.hkService.updateAssetLog(log.id, log).subscribe({
      next: () => {
        this.editingLogId = null;
        this.loadLogs();
      },
      error: err => console.error('Error updating log:', err)
    });
  }

  deleteLog(id: number) {
    if(confirm('Delete this maintenance log?')) {
      this.hkService.deleteAssetLog(id).subscribe(() => this.loadLogs());
    }
  }

  viewDetails(id: number) {
    // same as before
  }
}
