import { Component, OnInit } from '@angular/core';
import { MaintenanceRequest } from '../../models/maintenance-request.model';
import { HousekeepingService } from '../../services/housekeeping.service';

@Component({
  selector: 'app-maintenance-list',
  templateUrl: './maintenance-list.component.html',
  styleUrls: ['./maintenance-list.component.scss']
})
export class MaintenanceListComponent implements OnInit {

  requests: MaintenanceRequest[] = [];
  statuses = ['PENDING', 'IN_PROGRESS', 'DONE'];
  editingRequestId: number | null = null;

  constructor(private hkService: HousekeepingService) {}

  ngOnInit(): void {
    this.loadRequests();
  }

  loadRequests() {
    this.hkService.getAllMaintenance().subscribe(res => this.requests = res);
  }

  startEdit(id: number) {
    this.editingRequestId = id;
  }

  cancelEdit() {
    this.editingRequestId = null;
    this.loadRequests();
  }

  updateRequest(request: MaintenanceRequest) {
    this.hkService.updateMaintenanceStatus(request.id, request.status).subscribe({
      next: () => {
        this.editingRequestId = null;
        this.loadRequests();
      },
      error: err => console.error('Error updating request:', err)
    });
  }

  deleteRequest(id: number) {
    if(confirm('Are you sure you want to delete this request?')) {
      this.hkService.deleteMaintenance(id).subscribe(() => this.loadRequests());
    }
  }
}
