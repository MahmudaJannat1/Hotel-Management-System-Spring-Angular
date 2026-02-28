import { Component, OnInit } from '@angular/core';
import { LaundryRequest } from '../../models/laundry-request.model';
import { HousekeepingService } from '../../services/housekeeping.service';

@Component({
  selector: 'app-laundry-list',
  templateUrl: './laundry-list.component.html',
  styleUrls: ['./laundry-list.component.scss']
})
export class LaundryListComponent implements OnInit {

  laundryRequests: LaundryRequest[] = [];
  statuses = ['PENDING', 'IN_PROGRESS', 'DONE'];
  editId: number | null = null; // ✅ Track which row is in edit mode

  constructor(private hkService: HousekeepingService) {}

  ngOnInit(): void {
    this.loadLaundry();
  }

  loadLaundry() {
    this.hkService.getAllLaundry()
      .subscribe(res => this.laundryRequests = res);
  }

  startEdit(id: number) {
    this.editId = id; // Set row to edit mode
  }

  cancelEdit() {
    this.editId = null; // Cancel edit mode
  }

  updateStatus(id: number, status: string) {
    this.hkService.updateLaundryStatus(id, status)
      .subscribe({
        next: () => {
          this.loadLaundry(); 
          this.editId = null; // exit edit mode after update
        },
        error: err => console.error('Error updating status:', err)
      });
  }

  deleteRequest(id: number) {
    if(confirm('Are you sure you want to delete this laundry request?')) {
      this.hkService.deleteLaundry(id).subscribe({
        next: () => this.loadLaundry(),
        error: err => console.error(err)
      });
    }
  }
}

